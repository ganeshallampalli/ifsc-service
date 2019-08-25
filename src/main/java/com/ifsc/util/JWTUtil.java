package com.ifsc.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {

	@Value("${fyle.jwt.secret}")
	private String secret;

	public Boolean validateJWT(String jwt) {

		Claims claims;
		// This line will throw an exception if it is not a signed JWS (as expected)
		try {
			claims = Jwts.parser()
					.setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
					.parseClaimsJws(jwt.replace("Bearer ", ""))
					.getBody();
		} catch (Exception ex) {
			return false;
		}

		Date expiry = claims.getExpiration();
		Date currentDate = new Date();
		return currentDate.compareTo(expiry) < 0;
	}

	/*
	 * static void main(String[] args) {
	 * 
	 * Calendar cal = Calendar.getInstance(); cal.add(Calendar.DATE, -1); String s =
	 * Jwts.builder() .setSubject("FyleIFSC") .setId("Ganesh12345Fyle12345")
	 * .setIssuedAt(new Date()) .setExpiration(cal.getTime()) .claim("name",
	 * "Fyle Ganesh") .claim("admin", true) .signWith(SignatureAlgorithm.HS256,
	 * "fyle-secret".getBytes(StandardCharsets.UTF_8)) .compact();
	 * System.out.println(s); }
	 */
}
