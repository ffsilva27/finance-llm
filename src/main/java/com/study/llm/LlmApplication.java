
package com.study.llm;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.study.llm.infrastructure.rag.ContextDocumentReader;
import com.study.llm.infrastructure.rag.chunk.DocumentSectionParser;
import com.study.llm.infrastructure.rag.model.ContextDocument;

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
        if (runIngest) {
            try {
                List<ContextDocument> documents = contextDocumentReader.readAndValidate();

                for (ContextDocument document : documents) {
                    if ("B3".equals(document.metadata().source())
                            && "Perguntas frequentes | B3".equals(
                                    document.metadata().title())) {

                        List<String> sections = documentSectionParser.parse(document.content());

                        log.info("Documento: {}", document.metadata().title());
                        log.info("Total de seções: {}", sections.size());
                    }
                }

            } catch (IOException e) {
                log.error(
                        "Erro ao rodar a ingestão de documentos: {}",
                        e.getMessage(),
                        e);
            }
        }

        log.info("Running...");
    }
}
