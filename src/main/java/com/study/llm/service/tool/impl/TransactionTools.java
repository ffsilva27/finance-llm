package com.study.llm.service.tool.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.study.llm.service.tool.FinanceTools;
import com.study.llm.service.tool.model.TransactionDTO;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionTools implements FinanceTools {

    @Tool("Lista últimas transações")
    public TransactionDTO lastTransaction() {
        log.info("Buscando última transação...");
        return TransactionDTO.builder()
        .category("Comida")
        .establishment("KFC")
        .value(BigDecimal.valueOf(100))
        .build();
    }

}
