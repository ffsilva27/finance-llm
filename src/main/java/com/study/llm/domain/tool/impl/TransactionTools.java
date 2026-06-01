package com.study.llm.domain.tool.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.study.llm.domain.balance.service.TransactionServiceImpl;
import com.study.llm.domain.tool.FinanceTools;
import com.study.llm.domain.tool.model.TransactionDTO;

import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionTools implements FinanceTools {

    private final TransactionServiceImpl transactionServiceImpl;

    @Tool("Lista últimas 5 transações com base em um userId específico que pode ser obtido através da tool de identificação do usuário.")
    public List<TransactionDTO> lastTransaction(UUID userId) {
        log.info("Buscando última transação...");
        return transactionServiceImpl.getLast5TransactionsByCustomerId(userId);
    }

}
