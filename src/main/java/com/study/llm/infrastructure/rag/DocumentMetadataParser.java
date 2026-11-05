
package com.study.llm.infrastructure.rag;

import com.study.llm.infrastructure.rag.model.DocumentMetadata;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.LoaderOptions;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DocumentMetadataParser {

    private final Yaml yaml;

    public DocumentMetadataParser() {
        LoaderOptions loaderOptions = new LoaderOptions();

        this.yaml = new Yaml(
                new SafeConstructor(loaderOptions)
        );
    }

    public DocumentMetadata parse(String markdown) {

        String normalizedMarkdown = markdown.stripLeading();
    
        if (!normalizedMarkdown.startsWith("---")) {
            throw new IllegalArgumentException(
                    "Front Matter ausente ou inválido"
            );
        }
    
        int frontMatterStart = normalizedMarkdown.indexOf('\n') + 1;
    
        if (frontMatterStart == 0) {
            throw new IllegalArgumentException(
                    "Delimitador inicial do Front Matter inválido"
            );
        }
    
        int frontMatterEnd = -1;
        int searchFrom = frontMatterStart;
    
        while (searchFrom < normalizedMarkdown.length()) {
    
            int lineEnd = normalizedMarkdown.indexOf('\n', searchFrom);
    
            if (lineEnd == -1) {
                lineEnd = normalizedMarkdown.length();
            }
    
            String line = normalizedMarkdown
                    .substring(searchFrom, lineEnd)
                    .strip();
    
            if (line.equals("---")) {
                frontMatterEnd = searchFrom;
                break;
            }
    
            searchFrom = lineEnd + 1;
        }
    
        if (frontMatterEnd == -1) {
            throw new IllegalArgumentException(
                    "Delimitador final do Front Matter não encontrado"
            );
        }
    
        String frontMatter = normalizedMarkdown.substring(
                frontMatterStart,
                frontMatterEnd
        );
    
        Map<String, Object> metadataMap = null;
        try {
            metadataMap = yaml.load(frontMatter);
        } catch (YAMLException exception) {
            throw new IllegalArgumentException(
                    "Erro ao analisar o Front Matter: " + exception.getMessage()
            );
        }
    
        if (metadataMap == null) {
            throw new IllegalArgumentException(
                    "Front Matter vazio"
            );
        }
    
        DocumentMetadata documentMetadata = new DocumentMetadata(
                getValue(metadataMap, "title"),
                getValue(metadataMap, "source"),
                getValue(metadataMap, "url")
        );
        //TODO: Atentar que o content não é propagado, mas sim apenas os headers do front matter
        validateMetadata(documentMetadata);
        return documentMetadata;
    }

    private String getValue(Map<String, Object> metadata, String key) {

        Object value = metadata.get(key);

        return value == null ? null : value.toString().trim();
    }

    private void validateMetadata(DocumentMetadata metadata) {

        if (metadata.title() == null || metadata.title().isBlank()) {
            throw new IllegalArgumentException("Campo 'title' ausente ou vazio");
        }
    
        if (metadata.source() == null || metadata.source().isBlank()) {
            throw new IllegalArgumentException("Campo 'source' ausente ou vazio");
        }
    
        if (metadata.url() == null || metadata.url().isBlank()) {
            throw new IllegalArgumentException("Campo 'url' ausente ou vazio");
        }
    }
}
