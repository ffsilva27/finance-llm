package com.study.llm.infrastructure.rag.model;

public record DocumentMetadata(
        String title,
        String source,
        String url
) {
}