package com.study.llm.infrastructure.rag.chunk.model;

import com.study.llm.infrastructure.rag.section.model.DocumentSection;

public record DocumentChunk(
    String content,
    DocumentSection section,
    int index
) {
}
