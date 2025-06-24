package com.tc.reactor.support;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.tc.reactor.support.languages.HSL.*;

public class SyntaxManager {
    /**
     * Sets up syntax highlighting for the given programming language.
     */
    public void setupSyntaxHighlighting(String language, CodeArea codeArea) {
        // Fetch the keywords for the chosen language
        List<String> keywords = LANGUAGE_KEYWORDS.getOrDefault(language, Collections.emptyList());
        List<String> scopes = SCOPE_KEYWORDS.getOrDefault(language, Collections.emptyList());
        List<String> types = TYPES_KEYWORDS.getOrDefault(language, Collections.emptyList());
        List<String> blocks = BLOCKS_KEYWORDS.getOrDefault(language, Collections.emptyList());

        // Build the regex pattern dynamically
        String keywordPattern = "\\b(" + String.join("|", keywords) + ")\\b";
        String scopePattern = "\\b(" + String.join("|", scopes) + ")\\b";
        String typePattern = "\\b(" + String.join("|", types) + ")\\b";
        String blocksPattern = "\\b(" + String.join("|", blocks) + ")\\b";
        String fullPattern = String.join("|", COMMENT_PATTERN, STRING_PATTERN, NUMBER_PATTERN, keywordPattern,
                scopePattern, typePattern, blocksPattern);
        Pattern pattern = Pattern.compile(fullPattern);

        // Listen for text changes and apply syntax highlighting
        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            codeArea.setStyleSpans(0, computeHighlighting(newText, pattern));
        });
    }

    /**
     * Computes StyleSpans for syntax highlighting.
     */
    private StyleSpans<Collection<String>> computeHighlighting(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        int lastEnd = 0;

        while (matcher.find()) {
            // Add unstyled spans
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastEnd);

            // Apply styles based on matching groups
            if (matcher.group().matches(COMMENT_PATTERN)) {
                spansBuilder.add(Collections.singleton("comment"), matcher.end() - matcher.start());
            } else if (matcher.group().matches(STRING_PATTERN)) {
                spansBuilder.add(Collections.singleton("string"), matcher.end() - matcher.start());
            } else if (matcher.group().matches(NUMBER_PATTERN)) {
                spansBuilder.add(Collections.singleton("number"), matcher.end() - matcher.start());
            } else if (matcher.group().matches(KEYWORD_PATTERN)){
                spansBuilder.add(Collections.singleton("keyword"), matcher.end() - matcher.start());
            } else if (matcher.group().matches(SCOPE_PATTERN)){
                spansBuilder.add(Collections.singleton("scope"), matcher.end() - matcher.start());
            } else if (matcher.group().matches(TYPES_PATTERN)){
                spansBuilder.add(Collections.singleton("types"), matcher.end() - matcher.start());
            } else if (matcher.group().matches(BLOCKS_PATTERN)){
                spansBuilder.add(Collections.singleton("blocks"), matcher.end() - matcher.start());
            }

            lastEnd = matcher.end();
        }

        // Handle remaining, unstyled text
        spansBuilder.add(Collections.emptyList(), text.length() - lastEnd);
        return spansBuilder.create();
    }

    private static final String COMMENT_PATTERN = "//[^\n]*|/\\*(.|\\R)*?\\*/";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String NUMBER_PATTERN = "\\b(\\d+(\\.\\d+)?)\\b";

    private static final String KEYWORD_PATTERN = LANGUAGE_KEYWORDS.values().stream()
            .flatMap(Collection::stream)
            .map(keyword -> "\\b" + Pattern.quote(keyword) + "\\b")
            .collect(Collectors.joining("|"));

    private static final String SCOPE_PATTERN = SCOPE_KEYWORDS.values().stream()
            .flatMap(Collection::stream)
            .map(scope -> "\\b" + Pattern.quote(scope) + "\\b")
            .collect(Collectors.joining("|"));

    private static final String TYPES_PATTERN = TYPES_KEYWORDS.values().stream()
            .flatMap(Collection::stream)
            .map(type -> "\\b" + Pattern.quote(type) + "\\b")
            .collect(Collectors.joining("|"));

    private static final String BLOCKS_PATTERN = BLOCKS_KEYWORDS.values().stream()
            .flatMap(Collection::stream)
            .map(block -> "\\b" + Pattern.quote(block) + "\\b")
            .collect(Collectors.joining("|"));
}
