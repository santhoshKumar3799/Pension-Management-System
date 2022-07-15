package com.authorizationservie2.authorizationservice.jwtutilservices;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenManager implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(TokenManager.class);

	private static final long serialVersionUID = 7008375124389347049L;

	public static final long TOKEN_VALIDITY = 10 * 60 * 60;

	private String jwtSecret = "${jwt.secret}";

	/**
	 * This method generates token
	 * 
	 * @param Claims,
	 *            subject
	 * @return String token
	 */

	public String generateJwtToken(UserDetails userDetails) {
		logger.info("START");
		
		Map<String, Object> claims = new HashMap<String, Object>();
		
		logger.info("END");
		
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * This method validates token
	 * 
	 * @param String
	 *            token
	 * @return Boolean
	 */

	public Boolean validateJwtToken(String token) {
		logger.info("START");
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			
			logger.info("END");
			
			return true;
		} catch (Exception e) {
			logger.info("EXCEPTION");
			
			return false;
		}
	}

	public String extractUsername(String token) {
		logger.info("START");
		logger.info("END");
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		logger.info("START");
		
		final Claims claims = extractAllClaims(token);
		
		logger.info("END");
		
		return claimsResolver.apply(claims);

	}

	private Claims extractAllClaims(String token) {
		logger.info("START");
		logger.info("END");

		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

	}

}
