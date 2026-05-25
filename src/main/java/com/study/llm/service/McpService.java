package com.study.llm.service;

import org.springframework.stereotype.Service;

import com.study.llm.configuration.FinanceAssistant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class McpService {

    private final FinanceAssistant assistant;

    public String process(String userId, String userMessage) {
        log.info("Enviado mensagem para LLM...");
        var assistanteMessage =  assistant.chat(userId, userMessage);
        return assistanteMessage;
    }

}
