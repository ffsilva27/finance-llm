package com.study.llm.infrastructure.rag.chunk;

import java.util.List;

import org.springframework.stereotype.Component;

import com.study.llm.infrastructure.rag.chunk.model.DocumentChunk;
import com.study.llm.infrastructure.rag.reader.model.DocumentMetadata;
import com.study.llm.infrastructure.rag.section.model.DocumentSection;

import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenCountEstimator;

@Component
public class DocumentChunker {

    private final HuggingFaceTokenCountEstimator tokenCountEstimator;

    public DocumentChunker(
            HuggingFaceTokenCountEstimator tokenCountEstimator
    ) {
        this.tokenCountEstimator = tokenCountEstimator;
    }

    public List<DocumentChunk> chunk(
            DocumentSection section,
            DocumentMetadata metadata
    ) {
        int tokenCount = tokenCountEstimator.estimateTokenCountInText(
            section.content()
        );

        if (tokenCount <= 450) {
            return List.of(
                new DocumentChunk(
                    section.content(),
                    metadata,
                    section,
                    0
                )
            );
        }
    
        // Próxima etapa: tratar seções com mais de 450 tokens.
        return List.of();
    }
}