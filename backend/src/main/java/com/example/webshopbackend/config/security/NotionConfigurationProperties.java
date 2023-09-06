package com.example.webshopbackend.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("notion")
public record NotionConfigurationProperties(String codeForHashing) {
}
