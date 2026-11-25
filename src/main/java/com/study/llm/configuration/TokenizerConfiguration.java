package com.study.llm.configuration;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenCountEstimator;

@Configuration
public class TokenizerConfiguration {

    @Bean
    public HuggingFaceTokenCountEstimator huggingFaceTokenizer(
            @Value("${rag.embedding.tokenizer-path:models/multilingual-e5-small/tokenizer.json}")
            String tokenizerPath
    ) {
        return new HuggingFaceTokenCountEstimator(Path.of(tokenizerPath));
    }
}