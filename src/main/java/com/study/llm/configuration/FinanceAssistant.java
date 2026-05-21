package com.study.llm.configuration;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface FinanceAssistant {
    @SystemMessage("""
        Você é um assistent financeiro. Seja objetivo e amigável.
        """)
    String chat(@MemoryId String memoryId, @UserMessage String userMessage);

}
