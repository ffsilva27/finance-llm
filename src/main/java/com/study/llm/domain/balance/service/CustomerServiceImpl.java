package com.study.llm.domain.balance.service;

import org.springframework.stereotype.Service;

import com.study.llm.domain.tool.model.UserIdentifierDTO;
import com.study.llm.domain.tool.model.mapper.CustomerMapper;
import com.study.llm.infrastructure.persistence.entity.CustomerEntity;
import com.study.llm.infrastructure.persistence.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public UserIdentifierDTO getCustomerBySerialNumberIdentification(String serialNumber) {
        log.info("Buscando usuário no BD com base no serialNumber: {}", serialNumber);
        return this.repository.findBySerialNumberIdentification(serialNumber)
                .map(mapper::tDto)
                .orElse(null);
    }

}
