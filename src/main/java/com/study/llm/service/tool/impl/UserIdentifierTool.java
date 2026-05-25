package com.study.llm.service.tool.impl;

import org.springframework.stereotype.Component;

import com.study.llm.service.tool.FinanceTools;
import com.study.llm.service.tool.model.UserIdentifierDTO;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserIdentifierTool implements FinanceTools{

    @Tool("Através do número de identificação que o cliente pode fornecer, essa ferramenta concede o userId que é justamente a chave de identificação para as demais ferramentas.")
    public UserIdentifierDTO getUserIdBySerialNumberIdentifier(Long serialNumberIdentifier) {
        log.info("Obtendo número de identificação com base no serial number: {}", serialNumberIdentifier);
        return UserIdentifierDTO.builder()
        .serialNumberIdentifier(serialNumberIdentifier)
        .userId(1234L)
        .name("Filipe")
        .build();
    }

}
