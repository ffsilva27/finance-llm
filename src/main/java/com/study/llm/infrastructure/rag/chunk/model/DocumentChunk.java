package com.study.llm.infrastructure.rag.chunk.model;

import com.study.llm.infrastructure.rag.reader.model.DocumentMetadata;
import com.study.llm.infrastructure.rag.section.model.DocumentSection;

public record DocumentChunk(
    String content,
    DocumentMetadata metadata,
    DocumentSection section,
    int index
) {
}
