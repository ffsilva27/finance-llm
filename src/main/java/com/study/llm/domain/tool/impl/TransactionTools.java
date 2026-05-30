package com.study.llm.domain.tool.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.study.llm.domain.tool.FinanceTools;
import com.study.llm.domain.tool.model.TransactionDTO;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionTools implements FinanceTools {

    @Tool("Lista últimas 5 transações com base em um userId específico que pode ser obtido através da tool de identificação do usuário.")
    public List<TransactionDTO> lastTransaction(Long userId) {
        log.info("Buscando última transação...");
        var firstTransaction = TransactionDTO.builder()
        .userId(userId)
        .category("Comida")
        .establishment("KFC")
        .value(BigDecimal.valueOf(100))
        .effectiveDate(LocalDate.now().minusDays(1))
        .build();

        var secondTransaction = TransactionDTO.builder()
        .userId(userId)
        .category("Transporte")
        .establishment("Uber")
        .value(BigDecimal.valueOf(30))
        .effectiveDate(LocalDate.now())
        .build();
        
        return List.of(firstTransaction, secondTransaction);
    }

}
