package com.study.llm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AiConfig {

    private final OpenRouterConfiguration openRouterConfiguration;

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .baseUrl(openRouterConfiguration.getBaseUrl())
                .apiKey(openRouterConfiguration.getApiKey())
                .modelName(openRouterConfiguration.getModel())
                .logRequests(true)
                .logResponses(true)
                .temperature(0.7)
                .build();
    }

}
