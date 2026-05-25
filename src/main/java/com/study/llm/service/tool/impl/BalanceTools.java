package com.study.llm.service.tool.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.study.llm.service.tool.FinanceTools;
import com.study.llm.service.tool.model.BalanceDTO;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BalanceTools implements FinanceTools {

    @Tool("Consulta saldo da conta com base em um userId específico que pode ser obtido através da tool de identificação do usuário.")
    public BalanceDTO getBalance(Long userId) {
        log.info("Buscando saldo...");
        return new BalanceDTO(userId, BigDecimal.valueOf(1000));
    }
}
