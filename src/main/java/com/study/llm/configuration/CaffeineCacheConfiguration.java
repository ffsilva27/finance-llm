package com.study.llm.configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

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

        return cache.get(memoryId, id -> new ArrayList<>());
    }

    @Override
    public void updateMessages(Object memoryId,
                               List<ChatMessage> messages) {

        cache.put(memoryId, messages);
    }

    @Override
    public void deleteMessages(Object memoryId) {

        cache.invalidate(memoryId);
    }

}
