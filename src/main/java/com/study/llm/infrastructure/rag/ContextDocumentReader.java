
package com.study.llm.infrastructure.rag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.study.llm.infrastructure.rag.model.ContextDocument;
import com.study.llm.infrastructure.rag.model.DocumentMetadata;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Component
public class ContextDocumentReader {

    private static final String DOCUMENTS_PATTERN =
            "classpath*:context/**/*.md";

    private final ResourcePatternResolver resourcePatternResolver;
    private final DocumentMetadataParser metadataParser;
    private final DocumentContentParser contentParser;

    public ContextDocumentReader(
            ResourcePatternResolver resourcePatternResolver,
            DocumentMetadataParser metadataParser,
            DocumentContentParser contentParser) {
        this.resourcePatternResolver = resourcePatternResolver;
        this.metadataParser = metadataParser;
        this.contentParser = contentParser;
    }

    public List<ContextDocument> readAndValidate() throws IOException {

        Resource[] resources =
                resourcePatternResolver.getResources(DOCUMENTS_PATTERN);

        int totalDocuments = resources.length;
        int emptyDocuments = 0;
        int validDocuments = 0;
        int invalidMetadataDocuments = 0;
        int invalidContentDocuments = 0;
        List<String> invalidMetadataFileNames = new ArrayList<>();
        List<String> invalidContentFileNames = new ArrayList<>();
        List<ContextDocument> documents = new ArrayList<>();
        for (Resource resource : resources) {

            String content;

            try (InputStream inputStream = resource.getInputStream()) {
                content = new String(
                        inputStream.readAllBytes(),
                        StandardCharsets.UTF_8
                );
            }

            if (content.isBlank()) {
                emptyDocuments++;

                log.warn("Documento vazio encontrado: {}",
                        resource.getDescription());

                continue;
            }
            DocumentMetadata metadata;
            try {
                metadata = metadataParser.parse(content);
            } catch (IllegalArgumentException exception) {
                log.warn(
                        "Metadados inválidos no documento {}: {}",
                        resource.getFilename(),
                        exception.getMessage()
                );
                invalidMetadataDocuments++;
                invalidMetadataFileNames.add(resource.getFilename());
                continue;
            }
            try {
                String documentContent = contentParser.parse(content);
                if (documentContent.isBlank()) {
                    emptyDocuments++;
                
                    log.warn("Documento sem conteúdo encontrado: {}",
                            resource.getFilename());
                
                    continue;
                }
                ContextDocument contextDocument = new ContextDocument(
                    metadata,
                    documentContent
                );
                documents.add(contextDocument);
            } catch (IllegalArgumentException exception) {
                log.warn(
                        "Conteúdo inválido no documento {}: {}",
                        resource.getFilename(),
                        exception.getMessage()
                );
                invalidContentDocuments++;
                invalidContentFileNames.add(resource.getFilename());
                continue;
            }
            validDocuments++;
        }

        log.info("Leitura dos documentos finalizada.");
        log.info("Total de documentos: {}", totalDocuments);
        log.info("Documentos válidos: {}", validDocuments);
        log.info("Documentos vazios: {}", emptyDocuments);
        log.info("Documentos com metadados inválidos: {}",
        invalidMetadataDocuments);
        log.info("Documentos com conteúdo inválido: {}",
        invalidContentDocuments);
        log.info("Arquivos com conteúdo inválido: {}",
        String.join(", ", invalidContentFileNames));
        log.info("Arquivos com metadados inválidos: {}",
        String.join(", ", invalidMetadataFileNames));
        return documents;
    }
}
