package com.study.llm.controller.model;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest (
        @NotBlank
        String userId,

        @NotBlank
        String message   
) {}
