package com.study.llm.domain.balance.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.study.llm.domain.tool.model.BalanceDTO;
import com.study.llm.domain.tool.model.mapper.BalanceMapper;
import com.study.llm.infrastructure.persistence.repository.BalanceRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceServiceImpl {
    private final BalanceRepository repository;
    private final BalanceMapper mapper;

    public BalanceDTO getBalanceByUserId(@NonNull UUID customerId) {
        log.info("Buscando saldo no BD com base no userId: {}", customerId);
        return this.repository.findByCustomerId(customerId)
                .map(mapper::toDto)
                .orElse(null);
    }

}
