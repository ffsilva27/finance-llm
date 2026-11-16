package com.study.llm.infrastructure.rag.section.model;

public record DocumentSection(
    String category,
    String content,
    int index
) {
}
