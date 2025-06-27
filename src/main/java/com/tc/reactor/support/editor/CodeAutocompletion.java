package com.tc.reactor.support.editor;

import com.tc.reactor.support.languages.hsl.keywords;
import com.tc.reactor.support.languages.hsl.syntaxchecker.HslLexer;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.fxmisc.richtext.CodeArea;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeAutocompletion {
    private final CodeArea codeArea;
    private final Popup suggestionPopup;
    private final ListView<String> suggestionList;
    private final String language;
    private final Set<String> identifiers = new HashSet<>();

    public CodeAutocompletion(CodeArea codeArea, String language) {
        this.codeArea = codeArea;
        this.language = language;

        this.suggestionPopup = new Popup();
        this.suggestionList = new ListView<>();
        this.suggestionList.setPrefWidth(200);
        this.suggestionList.setPrefHeight(200);
        this.suggestionPopup.getContent().add(suggestionList);

        setupEventHandlers();

        updateIdentifiers(codeArea.getText());

        System.out.println("CodeAutocompletion initialized for " + language + " with " + identifiers.size() + " identifiers.");
    }

    private void setupEventHandlers() {
        codeArea.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            suggestionPopup.requestFocus();
            if (event.isControlDown() && event.getCode() == KeyCode.SPACE) {
                showAutocompletionSuggestions();
                event.consume();
            } else if (suggestionPopup.isShowing()) {
                switch (event.getCode()) {
                    case ESCAPE -> {
                        suggestionPopup.hide();
                        event.consume();
                    }
                    case TAB -> {
                        String selectedItem = suggestionList.getSelectionModel().getSelectedItem();
                        if (selectedItem != null && !selectedItem.isEmpty()) {
                            System.out.println("Selected Item: " + selectedItem); // Debug log
                            insertSelectedSuggestion();
                            event.consume();
                        } else {
                            System.out.println("No item selected."); // Debug log
                        }
                    }
                    case UP -> {
                        navigateSuggestionList(-1);
                        event.consume();
                    }
                    case DOWN -> {
                        navigateSuggestionList(1);
                        event.consume();
                    }
                }
            }
        });

        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            updateIdentifiers(newText);
            generateSuggestions(getCurrentWord());
            showAutocompletionSuggestions();
        });

        suggestionList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                insertSelectedSuggestion();
            }
        });

        // Scene attachment validation
        codeArea.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene == null) {
                System.err.println("CodeArea detached from Scene. Popup suggestions may not appear.");
            } else {
                System.out.println("CodeArea attached to Scene successfully.");
            }
        });
    }

    private void showAutocompletionSuggestions() {
        if (codeArea.getScene() == null) {
            System.err.println("CodeArea is not yet attached to a Scene.");
            return;
        }

        String currentWord = getCurrentWord();
        List<String> suggestions = generateSuggestions(currentWord);

        if (!suggestions.isEmpty()) {
            suggestionList.getItems().clear();
            suggestionList.getItems().addAll(suggestions);
            suggestionList.getSelectionModel().selectFirst();
            positionPopupAtCaret();
            suggestionPopup.show(codeArea.getScene().getWindow());
        } else {
            suggestionPopup.hide();
        }
    }

    private void positionPopupAtCaret() {
        Bounds caretBounds = codeArea.getCaretBounds().orElse(null);
        System.out.println("Caret Bounds: " + caretBounds);
        if (caretBounds == null) {
            System.err.println("Caret bounds are null.");
            suggestionPopup.setX(100);
            suggestionPopup.setY(100);
            return;
        }

        javafx.geometry.Point2D screenCoords = codeArea.localToScreen(caretBounds.getMinX(), caretBounds.getMinY());
        if (screenCoords == null) {
            System.err.println("Screen coordinates are null.");
            suggestionPopup.setX(100);
            suggestionPopup.setY(100);
            return;
        }

        System.out.println("Placing popup at: " + screenCoords);

        Platform.runLater(() -> {
            suggestionPopup.setX(caretBounds.getMinX());
            suggestionPopup.setY(caretBounds.getMaxY() + 5); // Optional offset for better visibility
        });
    }

    private void insertSelectedSuggestion() {
        String suggestion = suggestionList.getSelectionModel().getSelectedItem();
        if (suggestion != null) {
            System.out.println("Inserting suggestion: " + suggestion); // Debug log
            
            // Get the current word and its position
            String currentWord = getCurrentWord();
            int caretPosition = codeArea.getCaretPosition();
            int wordStart = caretPosition - currentWord.length();

            // Debugging current word bounds
//            System.out.println("Current Word: " + currentWord + ", Start Position: " + wordStart + ", Caret Position: " + caretPosition);

            // Replace the current word with the suggestion in the CodeArea
            try {
                codeArea.replaceText(wordStart, caretPosition, suggestion);
            } catch (Exception e) {
                System.err.println("Error while replacing text: " + e.getMessage());
            }

            // Hide the popup after inserting the suggestion
            suggestionPopup.hide();
        } else {
            System.out.println("No suggestion selected to insert."); // Debug log
        }
        codeArea.requestFocus();
    }

    private void navigateSuggestionList(int direction) {
        int currentIndex = suggestionList.getSelectionModel().getSelectedIndex();
        int newIndex = currentIndex + direction;

        if (newIndex >= 0 && newIndex < suggestionList.getItems().size()) {
            suggestionList.getSelectionModel().select(newIndex);
            suggestionList.scrollTo(newIndex);
        }
    }

    private String getCurrentWord() {
        int caretPosition = codeArea.getCaretPosition();
        String text = codeArea.getText();

        int wordStart = caretPosition;
        while (wordStart > 0 && isWordChar(text.charAt(wordStart - 1))) {
            wordStart--;
        }

        return text.substring(wordStart, caretPosition);
    }

    private boolean isWordChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    private List<String> generateSuggestions(String currentWord) {
        List<String> suggestions = new ArrayList<>();

        if ("hsl".equals(language)) {
            addMatchingKeywords(suggestions, keywords.LANGUAGE_KEYWORDS.get("hsl"), currentWord);
            addMatchingKeywords(suggestions, keywords.SCOPE_KEYWORDS.get("hsl"), currentWord);
            addMatchingKeywords(suggestions, keywords.TYPES_KEYWORDS.get("hsl"), currentWord);
            addMatchingKeywords(suggestions, keywords.BLOCKS_KEYWORDS.get("hsl"), currentWord);

            addMatchingIdentifiers(suggestions, currentWord);
        }

        Collections.sort(suggestions);

        return suggestions;
    }

    private void addMatchingKeywords(List<String> suggestions, List<String> keywords, String prefix) {
        if (keywords != null) {
            for (String keyword : keywords) {
                if (keyword.startsWith(prefix) && !suggestions.contains(keyword) && (!prefix.isEmpty())) {
                    suggestions.add(keyword);
                }
            }
        }
    }

    private void addMatchingIdentifiers(List<String> suggestions, String prefix) {
        for (String identifier : identifiers) {
            if (identifier.startsWith(prefix) && !suggestions.contains(identifier) && (!prefix.isEmpty()) && (!identifier.equals(prefix))) {
                suggestions.add(identifier);
            }
        }
    }

    private void updateIdentifiers(String text) {
        identifiers.clear();
        if ("hsl".equals(language)) {
            try {
                CharStream input = CharStreams.fromString(text);
                HslLexer lexer = new HslLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                tokens.fill();

                for (Token token : tokens.getTokens()) {
                    if (token.getType() == HslLexer.ID_LEX) {
                        identifiers.add(token.getText());
                    }
                }

                extractFunctionNames(text);
                extractVariableDeclarations(text);
            } catch (Exception e) {
                System.err.println("Error updating identifiers: " + e.getMessage());
            }
        }
    }

    private void extractFunctionNames(String text) {
        Pattern pattern = Pattern.compile("function\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            identifiers.add(matcher.group(1));
        }
    }

    private void extractVariableDeclarations(String text) {
        Pattern pattern = Pattern.compile("variable\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*[=;]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            identifiers.add(matcher.group(1));
        }

        String[] types = {"sequence", "string", "device", "resource", "timer", "dialog", "object", "event", "file"};
        for (String type : types) {
            pattern = Pattern.compile(type + "\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*[=;]");
            matcher = pattern.matcher(text);

            while (matcher.find()) {
                identifiers.add(matcher.group(1));
            }
        }
    }
}