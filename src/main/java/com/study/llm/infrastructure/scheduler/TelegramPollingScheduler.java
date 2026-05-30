package com.study.llm.infrastructure.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.study.llm.domain.McpService;
import com.study.llm.infrastructure.client.telegram.TelegramClient;
import com.study.llm.infrastructure.client.telegram.model.TelegramUpdate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramPollingScheduler {

    private final TelegramClient telegramClient;
    private final McpService mcpService;

    private Long offset = 0L;

    // @Scheduled(fixedDelay = 3000)
    public void poll() {

        var response = telegramClient.getTelegramMessage(offset);

        if (response == null || response.result() == null) {
            return;
        }

        for (TelegramUpdate update : response.result()) {

            offset = update.update_id() + 1;

            if (update.message() == null) {
                continue;
            }

            Long chatId = update.message()
                    .chat()
                    .id();

            String userMessage = update.message()
                    .text();

            log.info("ChatId: {} - Mensagem recebida: {}", chatId, userMessage);

            String assistantResponse = mcpService.process(
                    String.valueOf(chatId),
                    userMessage);

            telegramClient.sendMessage(
                    chatId,
                    assistantResponse);
        }
    }

}
