package com.study.llm.infrastructure.client.telegram.model;

public record TelegramUpdate(
        Long update_id,
        TelegramMessage message) {

}
