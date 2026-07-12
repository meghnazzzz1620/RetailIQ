package com.retailiq.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "RetailIQSecretKeyRetailIQSecretKeyRetailIQSecretKey";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(String email) {

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + 1000 * 60 * 60 * 24)
                )

                .signWith(getSigningKey(), SignatureAlgorithm.HS256)

                .compact();

    }

    public String extractUsername(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getSigningKey())

                .build()

                .parseClaimsJws(token)

                .getBody()

                .getSubject();

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        return extractUsername(token)
                .equals(userDetails.getUsername());

    }

}