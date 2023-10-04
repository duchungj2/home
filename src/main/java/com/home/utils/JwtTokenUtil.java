package com.home.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	@Value("${jwt.secret}")
	private String jwtSecretKey;
	
	@Value("${jwt.expired}")
	private int jwtExpired;

	public String generateToken(String email) {
		Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
	}
	
	private String createToken(Map<String, Object> claims, String email) {
		Date now = new Date(System.currentTimeMillis());
        Date expiration  = new Date(System.currentTimeMillis() + jwtExpired);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
	
	private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
  
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
  
    public Boolean validateToken(String token, String email) {
        final String emailToken = extractEmail(token);
        return (emailToken.equals(email)  && !isTokenExpired(token));
    }
}
