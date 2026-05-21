package com.study.llm.infrastructure.client.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.study.llm.infrastructure.client.telegram.model.TelegramSendMenssageRequest;
import com.study.llm.infrastructure.client.telegram.model.TelegramSendMessageResponse;
import com.study.llm.infrastructure.client.telegram.model.TelegramUpdateResponse;

@Component
public class TelegramClient {

    private final RestClient client;

    public TelegramClient(
        @Value("${telegram.token}") String token,
        @Value("${telegram.url}") String url) {

    this.client = RestClient.builder()
            .baseUrl(url + token)
            .build();
    }

    public TelegramUpdateResponse getTelegramMessage(Long offset) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getUpdates")
                        .queryParam("offset", offset)
                        .build())
                .retrieve()
                .body(TelegramUpdateResponse.class);
    }

    public TelegramSendMessageResponse sendMessage(
            Long chatId,
            String text) {

        TelegramSendMenssageRequest request = new TelegramSendMenssageRequest(chatId, text);

        return client.post()
                .uri("/sendMessage")
                .body(request)
                .retrieve()
                .body(TelegramSendMessageResponse.class);
    }

}
