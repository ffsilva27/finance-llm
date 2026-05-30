package com.study.llm.domain.tool.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.study.llm.domain.tool.model.UserIdentifierDTO;
import com.study.llm.infrastructure.persistence.entity.CustomerEntity;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "serialNumberIdentification", target = "serialNumberIdentifier")
    @Mapping(source = "name", target = "name")
    public UserIdentifierDTO tDto(CustomerEntity entity);

}
