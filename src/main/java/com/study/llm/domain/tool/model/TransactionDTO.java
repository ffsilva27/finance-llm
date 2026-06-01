package com.study.llm.domain.tool.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDTO {
    private UUID userId;
    private String category;
    private BigDecimal value;
    private String establishment;
    private LocalDate effectiveDate;
}
