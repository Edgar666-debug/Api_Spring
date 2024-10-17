package com.apirest.Api_Rest.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class JwtUtilService {

    private static final String JWY_SECRET_KEY = "759715795b808075643c36bb0c5d369435956e3b8287bfc3676260baf10bfffd";
    private static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 15;

    public String generateToken(UserDetails userDetails) {
        var claims = new HashMap<String, Object>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith( SignatureAlgorithm.HS256, JWY_SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token , UserDetails userDetails) {
        return extractClaims(token, Claims::getSubject).equals(userDetails.getUsername())
                && !extractClaims(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(JWY_SECRET_KEY).
                parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }


}
