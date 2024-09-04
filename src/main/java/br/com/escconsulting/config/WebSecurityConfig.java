package br.com.escconsulting.config;

import br.com.escconsulting.dto.OAuth2AccessTokenErrorResponse;
import br.com.escconsulting.exception.AccessTokenRequiredException;
import br.com.escconsulting.security.jwt.TokenAuthenticationFilter;
import br.com.escconsulting.security.oauth2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	private CustomOidcUserService customOidcUserService;

	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(AbstractHttpConfigurer::disable)
				.formLogin(AbstractHttpConfigurer::disable)
				.httpBasic(AbstractHttpConfigurer::disable)
				.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new RestAuthenticationEntryPoint()))
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								.requestMatchers(getPublicRequestMatchers()).permitAll()
								.anyRequest().authenticated()
				)
				.oauth2Login(oauth2Login ->
						oauth2Login
								.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.authorizationRequestRepository(cookieAuthorizationRequestRepository()))
								.redirectionEndpoint(redirectionEndpoint -> {})
								.userInfoEndpoint(userInfoEndpoint -> {
									userInfoEndpoint.oidcUserService(customOidcUserService);
									userInfoEndpoint.userService(customOAuth2UserService);
								})
								.tokenEndpoint(tokenEndpoint -> tokenEndpoint.accessTokenResponseClient(authorizationCodeTokenResponseClient()))
								.successHandler(oAuth2AuthenticationSuccessHandler)
								.failureHandler(oAuth2AuthenticationFailureHandler)
				);

		// Adicione nosso filtro de autenticação baseado em token personalizado
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	private RequestMatcher getPublicRequestMatchers() {
		return new OrRequestMatcher(
				new AntPathRequestMatcher("/"),
				new AntPathRequestMatcher("/mercado-pago/webhook"),
				new AntPathRequestMatcher("/error"),
				new AntPathRequestMatcher("/api/all"),
				new AntPathRequestMatcher("/api/auth/**"),
				new AntPathRequestMatcher("/oauth2/**"),
				new AntPathRequestMatcher("/user/forgotPasswordVerificationCode"),
				new AntPathRequestMatcher("/user/forgotPasswordValidated"),
				new AntPathRequestMatcher("/user/forgotPassword"),
				new AntPathRequestMatcher("/user/by/email/{email}"),
				new AntPathRequestMatcher("/file/{fileId}"),
				new AntPathRequestMatcher("/vehicle-brand"),
				new AntPathRequestMatcher("/vehicle-category"),
				new AntPathRequestMatcher("/vehicle/vehicle-brand/{vehicleBrandId}"),
				new AntPathRequestMatcher("/vehicle-model/vehicle/{vehicleId}"),
				new AntPathRequestMatcher("/customer-vehicle/{customerVehicleId}"),
				new AntPathRequestMatcher("/customer-vehicle/search/page"),
				new AntPathRequestMatcher("/customer-vehicle-review/by/customer-vehicle/{customerVehicleId}/customer/{customerId}"),
				new AntPathRequestMatcher("/customer-vehicle-review/all/by/customer-vehicle/{customerVehicleId}"),
				new AntPathRequestMatcher("/customer-vehicle-address/by/customer-vehicle/{customerVehicleId}"),
				new AntPathRequestMatcher("/customer-vehicle-address/by/customer-vehicle/{customerVehicleId}/address-type/{addressTypeName}"),
				new AntPathRequestMatcher("/customer-vehicle-file-photo/by/customer-vehicle/{customerVehicleId}"),
				new AntPathRequestMatcher("/customer-vehicle-file-photo/by/customer-vehicle/{customerVehicleId}/and/cover-photo")
		);
	}

	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}

	/*
	 * By default, Spring OAuth2 uses
	 * HttpSessionOAuth2AuthorizationRequestRepository to save the authorization
	 * request. But, since our service is stateless, we can't save it in the
	 * session. We'll save the request in a Base64 encoded cookie instead.
	 */
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	// This bean is load the user specific data when form login is used.
	//public UserDetailsService userDetailsService() {
	//	return userDetailsService;
	//}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
		OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
		//tokenResponseHttpMessageConverter.setAccessTokenResponseConverter(new OAuth2AccessTokenResponseConverterWithDefaults());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
					throw new AccessTokenRequiredException(
							new OAuth2AccessTokenErrorResponse(HttpStatus.valueOf(response.getStatusCode().value()), response.getStatusText(), null));
				}
				super.handleError(response);
			}
		});

		DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
		tokenResponseClient.setRestOperations(restTemplate);
		return tokenResponseClient;
	}
}
