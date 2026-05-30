package com.study.llm.domain.tool.model;

import java.math.BigDecimal;
import java.util.UUID;

public record BalanceDTO (
    UUID userId,
    BigDecimal balanceValue
) {

}
