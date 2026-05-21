package com.study.llm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class OpenRouterConfiguration {
    @Value("${spring.ai.open-router.base-url}")
    private String baseUrl;

    @Value("${spring.ai.open-router.api-key}")
    private String apiKey;

    @Value("${spring.ai.open-router.model}")
    private String model;
}
