package com.example.webshopbackend.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class TokenGenerator {

    private final NotionConfigurationProperties notionConfigurationProperties;
    private Map<String, String> tokens = new HashMap<>();

    public TokenGenerator(
            Boolean isAccessTokenNeeded,
            String username,
            List<String> roles,
            String requestUrl,
            NotionConfigurationProperties notionConfigurationProperties
    ) {
        this.notionConfigurationProperties = notionConfigurationProperties;

        String code = notionConfigurationProperties.codeForHashing();
        Algorithm algorithm = Algorithm.HMAC256(code.getBytes(StandardCharsets.UTF_8));

        JWTCreator.Builder tokenBuilder =
                JWT
                    .create()
                    .withSubject(username)
                    .withIssuer(requestUrl);

        String access_token =
                tokenBuilder
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withClaim("roles", roles)
                    .sign(algorithm);

        String refresh_token;

        if (isAccessTokenNeeded) {
            refresh_token =
                    tokenBuilder
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                        .sign(algorithm);
            tokens.put("access_token", access_token);
        } else {
            refresh_token =
                    tokenBuilder
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withClaim("roles", roles)
                        .sign(algorithm);
        }

        tokens.put("refresh_token", refresh_token);

        if (roles.contains("ROLE_ADMIN")) {
            tokens.put("role", "ROLE_ADMIN");
        } else {
            tokens.put("role", "ROLE_USER");
        }
    }
}
