package com.yathish.order_management_system.utility;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

@Component
public class JwtUtil {
    
    private final String SECRET_KEY = "OrderManagementSystemSecretKey_000000000";
    private final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    // Method to generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) //deprecated SignatureAlgorithm removed .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build();

            return parser.parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (WeakKeyException e) {
            // TODO Auto-generated catch block
            System.out.println("Weak Key Exception: " + e.getMessage());
        } catch (JwtException e) {
            // TODO Auto-generated catch block
            System.out.println("JWT Exception: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Illegal Argument Exception: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        System.out.println("Extracted Username: " + extractedUsername);
        System.out.println("Provided Username: " + username);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        JwtParser parser = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build();

        return parser.parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
}