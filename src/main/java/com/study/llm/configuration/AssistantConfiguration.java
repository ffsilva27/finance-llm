package com.study.llm.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.llm.service.tool.FinanceTools;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;

@Configuration
public class AssistantConfiguration {

    @Bean
    FinanceAssistant financeAssistant(ChatLanguageModel model, CaffeineCacheConfiguration store, List<FinanceTools> tools) {
        ChatMemoryProvider provider = memoryId ->
        MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(100)
                .chatMemoryStore(store)
                .build();

        return AiServices.builder(FinanceAssistant.class)
                .chatLanguageModel(model)
                .chatMemoryProvider(provider)
                .tools(tools)
                .build();
    }

}
