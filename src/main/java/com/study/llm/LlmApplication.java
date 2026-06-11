package com.study.llm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LlmApplication implements CommandLineRunner {  
	public static void main(String[] args) {
		SpringApplication.run(LlmApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Running...");
	}
	//TODO: Ideias para uso de RAG: Uma base para respostas sobre educação financeira e um categorizador das compras para ser usado ao adicionar uma compra.

}
