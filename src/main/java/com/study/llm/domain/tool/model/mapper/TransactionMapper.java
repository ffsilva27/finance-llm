package com.study.llm.domain.tool.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.study.llm.domain.tool.model.TransactionDTO;
import com.study.llm.infrastructure.persistence.entity.CustomerEntity;
import com.study.llm.infrastructure.persistence.entity.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "customer.id", target = "userId")
    @Mapping(source = "transactionValue", target = "value")
    @Mapping(
        target = "effectiveDate",
        expression = "java(entity.getEffectiveDate().toLocalDate())"
    )
    TransactionDTO toDto(TransactionEntity entity);

    List<TransactionDTO> toDtoList(List<TransactionEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target =  "customer", source = "entity")
    @Mapping(target = "transactionValue", source = "dto.value")
    @Mapping(
        target = "effectiveDate",
        expression = "java(dto.getEffectiveDate().atStartOfDay())"
    )
    TransactionEntity toEntity(TransactionDTO dto, CustomerEntity entity);
}
