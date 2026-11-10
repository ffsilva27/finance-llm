package com.study.llm.infrastructure.rag.reader.model;

public record ContextDocument(
        DocumentMetadata metadata,
        String content
) {
}