package com.study.llm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.llm.controller.model.ChatRequest;
import com.study.llm.domain.McpService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/ai/chat")
@RequiredArgsConstructor
public class ChatMessageController {

    private final McpService mcp;

    @PostMapping()
    public String chat(@RequestBody @Valid ChatRequest requestBody) {
        log.info("Mensagem recebida para processamento pelo MCP financeiro...");
        var response = mcp.process(requestBody.userId(), requestBody.message());
        return response;
    }

}
