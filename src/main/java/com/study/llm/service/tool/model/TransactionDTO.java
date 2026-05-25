package com.study.llm.service.tool.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private Long userId;
    private String category;
    private BigDecimal value;
    private String establishment;
    private LocalDate effectiveDate;
}
