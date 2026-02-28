package com.laurabailie.transactionservice.security;

import java.nio.charset.StandardCharsets;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@SecurityScheme(
    name = "bearerAuth",                          // ← arbitrary name, used below
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",                         // optional but recommended
    description = "JWT Authorization header using the Bearer scheme."
)

public class SecurityConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frame -> frame.disable()))  // dev H2

        .authorizeHttpRequests(auth -> auth
    // Swagger/OpenAPI & assets - FIRST
    .requestMatchers(
        "/v3/api-docs/**",          // Default OpenAPI JSON + YAML
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/swagger-resources/**",
        "/webjars/**"               // Critical for UI static files
    ).permitAll()

    // H2 dev console
    .requestMatchers("/h2-console/**").permitAll()

    // Login/public
    .requestMatchers("/api/auth/**").permitAll()

    // Protected
    .requestMatchers("/api/transactions/**").hasAuthority("USER")  // ← Use hasAuthority("USER")

    .anyRequest().authenticated()
)

        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt.decoder(jwtDecoder()))
        );

    return http.build();
}

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(
            jwtSecret.getBytes(StandardCharsets.UTF_8),
            "HmacSHA256"
        );
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");  // ← Tell it to read "roles" claim
        grantedAuthoritiesConverter.setAuthorityPrefix("");            // No "ROLE_" or "SCOPE_" prefix

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }
}