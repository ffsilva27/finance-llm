package com.study.llm.infrastructure.client.telegram.model;

public record TelegramSendMenssageRequest(
        Long chat_id,
        String text) {

}
