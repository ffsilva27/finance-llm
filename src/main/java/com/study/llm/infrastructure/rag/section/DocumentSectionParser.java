
package com.study.llm.infrastructure.rag.section;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.study.llm.infrastructure.rag.section.model.CategoryBoundary;
import com.study.llm.infrastructure.rag.section.model.DocumentSection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentSectionParser {

    private static final Pattern QUESTION_START = Pattern.compile("(?m)^[ \t]*-[ \t]*(\\d+)[ \t]*(?:-|\\.)[ \t]*");

    public List<DocumentSection> parse(String content) {
        Matcher matcher = QUESTION_START.matcher(content);

        if (!matcher.find()) {
            return List.of(new DocumentSection(null, content.trim(), 0));
        }
        
        matcher.reset();

        List<DocumentSection> sections = new ArrayList<>();

        int currentQuestionStart = -1;
        int previousQuestionEnd = 0;
        String currentCategory = null;

        while (matcher.find()) {
            int questionNumber = Integer.parseInt(matcher.group(1));

            int sectionEnd = matcher.start();

            CategoryBoundary newCategory = null;

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

                sections.add(new DocumentSection(currentCategory, sectionContent, sections.size()));
            }

            if (newCategory != null) {
                currentCategory = newCategory.title();
            }

            currentQuestionStart = matcher.start();
            previousQuestionEnd = matcher.end();
        }

        if (currentQuestionStart >= 0) {
            String sectionContent = content.substring(currentQuestionStart).trim();

            sections.add(new DocumentSection(
                currentCategory,
                sectionContent,
                sections.size()));
        }

        log.info("Total de seções identificadas: {}", sections.size());

        return sections;
    }

    private CategoryBoundary extractCategory(
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

                return new CategoryBoundary(title, categoryStart);
            }

            end = lineStart - 1;
        }

        return null;
    }
}
