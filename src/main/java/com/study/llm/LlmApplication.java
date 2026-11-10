
package com.study.llm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.study.llm.infrastructure.rag.reader.ContextDocumentReader;
import com.study.llm.infrastructure.rag.section.DocumentSectionParser;
import com.study.llm.infrastructure.rag.reader.model.ContextDocument;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class LlmApplication implements CommandLineRunner {

    private final ContextDocumentReader contextDocumentReader;
    private final DocumentSectionParser documentSectionParser;

    @Value("${rag.run-ingest:true}")
    private boolean runIngest;

    public LlmApplication(
            ContextDocumentReader contextDocumentReader,
            DocumentSectionParser documentSectionParser) {
        this.contextDocumentReader = contextDocumentReader;
        this.documentSectionParser = documentSectionParser;
    }

    public static void main(String[] args) {
        SpringApplication.run(LlmApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (!runIngest) {
            log.info("Ingestão desabilitada.");
            return;
        }

        try {
            List<ContextDocument> documents = contextDocumentReader.readAndValidate();

            log.info("Total de documentos encontrados: {}", documents.size());

            Map<String, Integer> documentsBySource = new HashMap<>();
            Map<String, Integer> sectionsBySource = new HashMap<>();

            for (ContextDocument document : documents) {
                String source = document.metadata().source();

                List<String> sections = documentSectionParser.parse(document.content());

                documentsBySource.merge(source, 1, Integer::sum);
                sectionsBySource.merge(source, sections.size(), Integer::sum);
            }

            log.info("========== RESUMO DA INGESTÃO ==========");

            documentsBySource.forEach((source, totalDocuments) -> log.info(
                    "Fonte: {} | Documentos: {} | Seções: {}",
                    source,
                    totalDocuments,
                    sectionsBySource.get(source)));

        } catch (IOException e) {
            log.error(
                    "Erro ao rodar a ingestão de documentos: {}",
                    e.getMessage(),
                    e);
        }

        log.info("Running...");
    }
}
