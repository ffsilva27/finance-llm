package com.study.llm.domain.tool.impl;

import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.study.llm.domain.balance.service.BalanceServiceImpl;
import com.study.llm.domain.tool.FinanceTools;
import com.study.llm.domain.tool.model.BalanceDTO;

import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BalanceTools implements FinanceTools {

    private final BalanceServiceImpl balanceServiceImpl;

    @Tool("Consulta saldo da conta com base em um userId específico que pode ser obtido através da tool de identificação do usuário.")
    public BalanceDTO getBalance(UUID userId) {
        log.info("Buscando saldo...");
        var balance = balanceServiceImpl.getBalanceByUserId(userId);
        if (Objects.isNull(balance)) {
            throw new IllegalArgumentException("Saldo não identificado com base nos parametros passados.");
        }
        return balance;
    }
}
