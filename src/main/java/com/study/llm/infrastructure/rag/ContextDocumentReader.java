
package com.study.llm.infrastructure.rag;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Component
public class ContextDocumentReader {

    private static final String DOCUMENTS_PATTERN =
            "classpath*:context/**/*.md";

    private final ResourcePatternResolver resourcePatternResolver;

    public ContextDocumentReader(
            ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    public void readAndValidate() throws IOException {

        Resource[] resources =
                resourcePatternResolver.getResources(DOCUMENTS_PATTERN);

        int totalDocuments = resources.length;
        int emptyDocuments = 0;
        int validDocuments = 0;

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
            log.info("Documento válido encontrado: {}",
                    resource.getDescription());
            validDocuments++;
        }

        log.info("Leitura dos documentos finalizada.");
        log.info("Total de documentos: {}", totalDocuments);
        log.info("Documentos válidos: {}", validDocuments);
        log.info("Documentos vazios: {}", emptyDocuments);
    }
}
