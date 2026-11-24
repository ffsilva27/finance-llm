package com.study.llm.infrastructure.rag.chunk;

import java.util.List;

import org.springframework.stereotype.Component;

import com.study.llm.infrastructure.rag.chunk.model.DocumentChunk;
import com.study.llm.infrastructure.rag.reader.model.DocumentMetadata;
import com.study.llm.infrastructure.rag.section.model.DocumentSection;

@Component 
public class DocumentChunker {

    public List<DocumentChunk> chunk(
        DocumentSection section,
        DocumentMetadata metadata
    ) {
        return List.of(
            new DocumentChunk(
                section.content(),
                metadata,
                section,
                0
            )
        );
    }

}
