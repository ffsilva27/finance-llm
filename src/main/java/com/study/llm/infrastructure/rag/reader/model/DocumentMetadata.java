package com.study.llm.infrastructure.rag.reader.model;

public record DocumentMetadata(
        String title,
        String source,
        String url
) {
}