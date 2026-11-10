
package com.study.llm.infrastructure.rag.section;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.study.llm.infrastructure.rag.section.model.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentSectionParser {

    private static final Pattern QUESTION_START = Pattern.compile("(?m)^[ \t]*-[ \t]*(\\d+)[ \t]*(?:-|\\.)[ \t]*");

    public List<String> parse(String content) {
        log.info("Iniciando parse da seção do documento");

        Matcher matcher = QUESTION_START.matcher(content);

        if (!matcher.find()) {
            log.info("Nenhuma pergunta numerada encontrada. Mantendo documento como uma única seção.");
        
            return List.of(content.trim());
        }
        
        matcher.reset();

        List<String> sections = new ArrayList<>();

        int currentQuestionStart = -1;
        int previousQuestionEnd = 0;
        String currentCategory = null;

        while (matcher.find()) {
            int questionNumber = Integer.parseInt(matcher.group(1));

            int sectionEnd = matcher.start();

            Category newCategory = null;

            if (questionNumber == 1) {
                newCategory = extractCategory(
                        content,
                        previousQuestionEnd,
                        matcher.start());

                if (newCategory != null) {
                    sectionEnd = newCategory.start();
                }
            }

            if (currentQuestionStart >= 0 && sectionEnd > currentQuestionStart) {
                String sectionContent = content.substring(
                        currentQuestionStart,
                        sectionEnd).trim();

                sections.add(
                        "Categoria: " + currentCategory + "\n" + sectionContent);
            }

            if (newCategory != null) {
                currentCategory = newCategory.title();
            }

            currentQuestionStart = matcher.start();
            previousQuestionEnd = matcher.end();
        }

        if (currentQuestionStart >= 0) {
            String sectionContent = content.substring(currentQuestionStart).trim();

            sections.add(
                    "Categoria: " + currentCategory + "\n" + sectionContent);
        }

        log.info("Total de seções identificadas: {}", sections.size());

        return sections;
    }

    private Category extractCategory(
            String content,
            int previousQuestionEnd,
            int questionPosition) {

        String previousContent = content.substring(
                previousQuestionEnd,
                questionPosition);

        int end = previousContent.length();

        while (end > 0) {
            int lineStart = previousContent.lastIndexOf('\n', end - 1) + 1;

            String line = previousContent.substring(lineStart, end);
            String title = line.trim();

            if (!title.isEmpty()) {
                int titleOffset = line.indexOf(title);
                int categoryStart = previousQuestionEnd + lineStart + titleOffset;

                return new Category(title, categoryStart);
            }

            end = lineStart - 1;
        }

        return null;
    }
}
