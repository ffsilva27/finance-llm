package com.study.llm.infrastructure.client.telegram.model;

public record TelegramMessage(
        Long message_id,
        TelegramChat chat,
        String text) {

}
