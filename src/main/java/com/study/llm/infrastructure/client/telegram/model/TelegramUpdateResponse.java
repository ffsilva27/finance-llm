package com.study.llm.infrastructure.client.telegram.model;

import java.util.List;

public record TelegramUpdateResponse(
        Boolean ok,
        List<TelegramUpdate> result) {

}
