package com.study.llm.service.tool.impl;

import org.springframework.stereotype.Component;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BalanceTools {

    @Tool("Consulta saldo da conta")
    public String getBalance() {
        log.info("Buscando saldo...");
        return "Saldo: R$5000";
    }
}
