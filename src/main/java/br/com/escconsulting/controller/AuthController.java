package br.com.escconsulting.controller;

import br.com.escconsulting.dto.*;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.exception.BadRequestException;
import br.com.escconsulting.exception.UserAlreadyExistAuthenticationException;
import br.com.escconsulting.security.jwt.TokenProvider;
import br.com.escconsulting.service.UserService;
import br.com.escconsulting.util.GeneralUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final PasswordEncoder passwordEncoder;

	private final TokenProvider tokenProvider;

	private final UserService userService;

	private final ErrorResponseFactory errorResponseFactory;

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {

		// Verifica se o e-mail existe no banco de dados
		Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());
		if (!userOptional.isPresent()) {
			// Agora você pode passar apenas o código da mensagem
			throw new BadRequestException("auth.error.invalid.email");
		}

		User user = userOptional.get();

		// Verifica se a senha fornecida é correta
		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			// Agora você pode passar apenas o código da mensagem
			throw new BadRequestException("auth.error.invalid.password");
		}

		// Autentica o usuário usando o e-mail e a senha fornecidos
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Gera o token JWT para o usuário autenticado
		String jwt = tokenProvider.createToken(authentication);
		LocalUser localUser = (LocalUser) authentication.getPrincipal();

		// Retorna a resposta de autenticação com o token JWT e as informações do usuário
		return ResponseEntity.ok(new JWTAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			userService.registerNewUser(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Occurred", e);
			// Usa a fábrica para criar o ErrorResponse
			ErrorResponse errorResponse = errorResponseFactory.createErrorResponse("auth.error.email.already.in.use");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		// Usa a chave da mensagem para o APIResponse
		return ResponseEntity.ok().body(new APIResponse(true, "auth.success.user.registered"));
	}
}