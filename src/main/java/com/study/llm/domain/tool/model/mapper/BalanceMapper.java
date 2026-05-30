package com.study.llm.domain.tool.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.study.llm.domain.tool.model.BalanceDTO;
import com.study.llm.infrastructure.persistence.entity.BalanceEntity;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    @Mapping(source = "entity.customer.id", target = "userId")
    public BalanceDTO toDto(BalanceEntity entity);

}
