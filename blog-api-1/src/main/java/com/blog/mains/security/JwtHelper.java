package com.blog.mains.security;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {
	private static final String SECRET="90B7875B705F6DA58C6B8140BD5C4C542A08986C9D553F88D27B2EE58D9E10078362E35814E3BEFA7B4EDB74E59B4256BB297A9FFFB8B98F6AFC5A4CC13EA2B0";	
	private static final long validatity=TimeUnit.MINUTES.toMillis(30);
	public String genrateToken(UserDetails userDetails)
	{
		Map<String , Object>claims=new HashMap<String, Object>();
		claims.put("sub", userDetails.getUsername());
		claims.put("iss", "https://secure.genuinecoder.com");
		return doGenrateToken(claims,userDetails.getUsername());
	}
	private String doGenrateToken(Map<String, Object>claims,String subject)
	{
		return Jwts.builder()
					.claims(claims)
					.header().empty().add("typ","JWT")
					.and()
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis()+1000*60*60))
					.signWith(getSigningKey())
					.compact();
	}

	private SecretKey getSigningKey() {
		SecretKey hmacShaKeyFor = Keys.hmacShaKeyFor(SECRET.getBytes());
		return hmacShaKeyFor;
	}
	public String extractUsername(String jwt) {
		Claims claims = getClaims(jwt);
		return claims.getSubject();
	}
	public Claims getClaims(String jwt)
	{
		return Jwts.parser()
					 .verifyWith(getSigningKey())
					 .build()
					 .parseSignedClaims(jwt)
					 .getPayload();
	}
	public boolean isTokenValid(String jwt) {
		Claims claims = getClaims(jwt);
		return claims.getExpiration().after(Date.from(Instant.now()));
	}
}
