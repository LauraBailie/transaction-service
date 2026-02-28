package com.laurabailie.transactionservice.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long expirationMs;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        // TODO: Real auth (check username/password against DB)
        // For demo: accept any non-empty credentials
        String username = credentials.get("username");
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        Instant now = Instant.now();
        String token = Jwts.builder()
            .subject(username)
            .claim("roles", "USER")  // Add real roles later
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationMs, ChronoUnit.MILLIS)))
            .signWith(key)
            .compact();

        return ResponseEntity.ok(Map.of("token", token));
    }
}