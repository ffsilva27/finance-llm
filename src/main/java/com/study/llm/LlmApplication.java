package com.study.llm;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.study.llm.infrastructure.rag.ContextDocumentReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@SpringBootApplication
@EnableScheduling
public class LlmApplication implements CommandLineRunner {  

	private final ContextDocumentReader contextDocumentReader;

    @Value("${rag.run-ingest:true}")
    private boolean runIngest;

    public LlmApplication(ContextDocumentReader contextDocumentReader) {
        this.contextDocumentReader = contextDocumentReader;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(LlmApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (runIngest) {
            try {
                contextDocumentReader.readAndValidate();
            } catch (IOException e) {
				log.error("Erro ao rodar a ingestão de documentos: {}", e.getMessage());
            }
        }
        log.info("Running...");
	}
	//TODO: Ideias para uso de RAG: Uma base para respostas sobre educação financeira e um categorizador das compras para ser usado ao adicionar uma compra.

}
