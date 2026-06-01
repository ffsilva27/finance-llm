package com.study.llm.configuration;

import java.time.LocalDate;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface FinanceAssistant {
    @SystemMessage("""
        Você é um assistent financeiro. Seja objetivo e amigável.
        Caso precise de contexto relacionado a data, a data de hoje é: {{currentDate}}
        """)
    String chat(@MemoryId String memoryId, @UserMessage String userMessage, @V("currentDate") LocalDate currDate);

}
