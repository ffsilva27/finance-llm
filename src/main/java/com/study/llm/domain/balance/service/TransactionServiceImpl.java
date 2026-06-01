package com.study.llm.domain.balance.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.llm.domain.tool.model.TransactionDTO;
import com.study.llm.domain.tool.model.mapper.TransactionMapper;
import com.study.llm.infrastructure.persistence.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    public List<TransactionDTO> getLast5TransactionsByCustomerId(UUID customerId) {
        log.info("Obtendo as ultimas transações do customerId: {}", customerId);
        var last5Transactions = this.repository.findLastFiveRecentsTransactionByCustomerId(customerId, PageRequest.of(0, 5));
        return mapper.toDtoList(last5Transactions);
    }

}
