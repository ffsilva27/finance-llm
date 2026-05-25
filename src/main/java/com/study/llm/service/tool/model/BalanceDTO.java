package com.study.llm.service.tool.model;

import java.math.BigDecimal;

public record BalanceDTO (
    Long userId,
    BigDecimal balanceValue
) {

}
