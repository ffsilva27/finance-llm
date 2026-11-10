package com.study.llm.infrastructure.rag.reader;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Component 
public class DocumentContentParser {

    public String parse(String markdown) {

        String normalizedMarkdown = markdown.stripLeading();
    
        int frontMatterStart = normalizedMarkdown.indexOf('\n') + 1;
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
                frontMatterEnd = lineEnd;
                break;
            }
    
            searchFrom = lineEnd + 1;
        }
    
        if (frontMatterEnd == -1) {
            throw new IllegalArgumentException(
                    "Delimitador final do Front Matter não encontrado"
            );
        }
    
        return normalizedMarkdown.substring(frontMatterEnd).strip();
    }

}
