package com.study.llm.configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CaffeineCacheConfiguration implements ChatMemoryStore {

    private final Cache<Object, List<ChatMessage>> cache;

    public CaffeineCacheConfiguration() {
        this.cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterAccess(Duration.ofHours(2))
                .build();
    }

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        log.info("Obtendo contexto referente ao memoryId: {}", memoryId);
        return cache.get(memoryId, id -> new ArrayList<>());
    }

    @Override
    public void updateMessages(Object memoryId,
                               List<ChatMessage> messages) {
        log.info("Atualizando contexto referente ao memoryId: {}", memoryId);
        cache.put(memoryId, messages);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        log.info("Invelidando contexto referente ao memoryId: {}", memoryId);
        cache.invalidate(memoryId);
    }

}
