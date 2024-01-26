package br.com.escconsulting.security.jwt;

import br.com.escconsulting.config.AppProperties;
import br.com.escconsulting.dto.LocalUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

	private AppProperties appProperties;

	public TokenProvider(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	/*
	public String createToken(Authentication authentication) {
		LocalUser userPrincipal = (LocalUser) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

		return Jwts.builder().setSubject(Long.toString(userPrincipal.getUser().getId())).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.ES512, appProperties.getAuth().getTokenSecret()).compact();
	}
	*/

	/*
	public String createToken(Authentication authentication) {
		LocalUser userPrincipal = (LocalUser) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

		Algorithm algorithm = Algorithm.HMAC256(appProperties.getAuth().getTokenSecret());
		return JWT.create()
				.withSubject(userPrincipal.getUser().getId().toString())
				.withIssuedAt(new Date())
				.withExpiresAt(expiryDate)
				.sign(algorithm);
	}
	*/

	public String createToken(Authentication authentication) {
		LocalUser userPrincipal = (LocalUser) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

		Algorithm algorithm = Algorithm.HMAC256(appProperties.getAuth().getTokenSecret());

		// Adicionando informações de autorização ao JWT
		String[] authorities = userPrincipal.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toArray(String[]::new);

		return JWT.create()
				.withSubject(userPrincipal.getUser().getUserId().toString())
				.withIssuedAt(new Date())
				.withExpiresAt(expiryDate)
				.withArrayClaim("roles", authorities) // Adicionando as permissões como um array de strings
				.sign(algorithm);
	}

	/*
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();

		return Long.parseLong(claims.getSubject());
	}
	*/

	public UUID getUserIdFromToken(String token) throws JWTVerificationException {
		DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(appProperties.getAuth().getTokenSecret())).build().verify(token);
		return UUID.fromString(decodedJWT.getSubject());
	}

	/*
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}
	*/

	public boolean validateToken(String authToken) {
		try {
			DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(appProperties.getAuth().getTokenSecret())).build().verify(authToken);
			return true;
		} catch (JWTVerificationException ex) {
			logger.error("Invalid JWT token: " + ex.getMessage());
		}
		return false;
	}
}