package com.study.llm.infrastructure.rag.model;

public record ContextDocument(
        DocumentMetadata metadata,
        String content
) {
}