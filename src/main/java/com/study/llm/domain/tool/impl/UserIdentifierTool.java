package com.study.llm.domain.tool.impl;

import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.study.llm.domain.balance.service.CustomerServiceImpl;
import com.study.llm.domain.tool.FinanceTools;
import com.study.llm.domain.tool.model.UserIdentifierDTO;
import com.study.llm.infrastructure.persistence.entity.CustomerEntity;

import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserIdentifierTool implements FinanceTools {

    private final CustomerServiceImpl customerServiceImpl;

    @Tool("Através do número de identificação que o cliente pode fornecer, essa ferramenta concede o userId que é justamente a chave de identificação para as demais ferramentas.")
    public UserIdentifierDTO getUserIdBySerialNumberIdentifier(String serialNumberIdentifier) {
        log.info("Obtendo número de identificação com base no serial number: {}", serialNumberIdentifier);
        var user = customerServiceImpl.getCustomerBySerialNumberIdentification(serialNumberIdentifier);

        if(Objects.isNull(user)) {
            throw new IllegalArgumentException("Usuário não identificado com base nos parametros passados.");
        }
        return user;
    }

}
