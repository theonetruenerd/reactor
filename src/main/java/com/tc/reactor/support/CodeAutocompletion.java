package com.tc.reactor.support;

import com.tc.reactor.support.languages.hsl.keywords;
import com.tc.reactor.support.languages.hsl.syntaxchecker.HslLexerLexer;
import com.tc.reactor.support.languages.hsl.syntaxchecker.HslLexerParser;
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
import java.util.stream.Collectors;

public class CodeAutocompletion {
    private final CodeArea codeArea;
    private final Popup suggestionPopup;
    private final ListView<String> suggestionList;
    private final String language;
    private final Set<String> identifiers = new HashSet<>();

    /**
     * Creates a new CodeAutocompletion instance for the given CodeArea and language.
     * 
     * @param codeArea The CodeArea to add autocompletion to
     * @param language The programming language to provide autocompletion for
     */
    public CodeAutocompletion(CodeArea codeArea, String language) {
        this.codeArea = codeArea;
        this.language = language;

        // Create the suggestion popup and list
        this.suggestionPopup = new Popup();
        this.suggestionList = new ListView<>();
        this.suggestionList.setPrefWidth(200);
        this.suggestionList.setPrefHeight(200);
        this.suggestionPopup.getContent().add(suggestionList);

        // Set up event handlers
        setupEventHandlers();

        // Perform initial scan of the text
        updateIdentifiers(codeArea.getText());

        // Log initialization for debugging
        System.out.println("CodeAutocompletion initialized for " + language + " with " + identifiers.size() + " identifiers");
    }

    /**
     * Sets up the event handlers for triggering and handling autocompletion.
     */
    private void setupEventHandlers() {
        // Listen for key events that trigger autocompletion
        codeArea.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.SPACE) {
                // Ctrl+Space triggers autocompletion
                showAutocompletionSuggestions();
                event.consume();
            } else if (suggestionPopup.isShowing()) {
                // Handle navigation in the suggestion list
                switch (event.getCode()) {
                    case ESCAPE:
                        suggestionPopup.hide();
                        event.consume();
                        break;
                    case ENTER:
                        if (!suggestionList.getSelectionModel().isEmpty()) {
                            insertSelectedSuggestion();
                            event.consume();
                        }
                        break;
                    case UP:
                        navigateSuggestionList(-1);
                        event.consume();
                        break;
                    case DOWN:
                        navigateSuggestionList(1);
                        event.consume();
                        break;
                }
            }
        });

        // Listen for text changes to update the identifier list
        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            updateIdentifiers(newText);
        });

        // Handle mouse clicks on the suggestion list
        suggestionList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                insertSelectedSuggestion();
            }
        });
    }

    /**
     * Shows the autocompletion suggestions popup at the current caret position.
     */
    private void showAutocompletionSuggestions() {
        // Get the current word being typed
        String currentWord = getCurrentWord();

        // Generate suggestions based on the current word
        List<String> suggestions = generateSuggestions(currentWord);

        if (!suggestions.isEmpty()) {
            // Update the suggestion list
            suggestionList.getItems().clear();
            suggestionList.getItems().addAll(suggestions);
            suggestionList.getSelectionModel().selectFirst();

            // Position the popup at the caret
            positionPopupAtCaret();

            // Show the popup
            suggestionPopup.show(codeArea.getScene().getWindow());
        }
    }

    /**
     * Positions the suggestion popup at the current caret position.
     */
    private void positionPopupAtCaret() {
        Bounds caretBounds = codeArea.getCaretBounds().orElse(null);
        if (caretBounds == null) {
            System.err.println("Caret bounds not found! Popup cannot be positioned.");
            return;
        }

        double x = caretBounds.getMinX();
        double y = caretBounds.getMaxY();

        // Log for debugging
        System.out.printf("Popup positioning at X: %.2f, Y: %.2f%n", x, y);

        javafx.geometry.Point2D screenCoords = codeArea.localToScreen(x, y);

        if (screenCoords != null) {
            suggestionPopup.setX(screenCoords.getX());
            suggestionPopup.setY(screenCoords.getY());
        } else {
            System.err.println("Screen coordinates could not be calculated.");
        }
    }

    /**
     * Inserts the currently selected suggestion into the code area.
     */
    private void insertSelectedSuggestion() {
        String suggestion = suggestionList.getSelectionModel().getSelectedItem();
        if (suggestion != null) {
            // Get the current word and its position
            String currentWord = getCurrentWord();
            int caretPosition = codeArea.getCaretPosition();
            int wordStart = caretPosition - currentWord.length();

            // Replace the current word with the suggestion
            codeArea.replaceText(wordStart, caretPosition, suggestion);

            // Hide the popup
            suggestionPopup.hide();
        }
    }

    /**
     * Navigates the suggestion list up or down.
     * 
     * @param direction 1 for down, -1 for up
     */
    private void navigateSuggestionList(int direction) {
        int currentIndex = suggestionList.getSelectionModel().getSelectedIndex();
        int newIndex = currentIndex + direction;

        if (newIndex >= 0 && newIndex < suggestionList.getItems().size()) {
            suggestionList.getSelectionModel().select(newIndex);
            suggestionList.scrollTo(newIndex);
        }
    }

    /**
     * Gets the current word being typed at the caret position.
     * 
     * @return The current word
     */
    private String getCurrentWord() {
        int caretPosition = codeArea.getCaretPosition();
        String text = codeArea.getText();

        // Log the text near the caret for debugging
        System.out.printf("Text around caret: '%s'%n", text.substring(Math.max(0, caretPosition - 10), Math.min(text.length(), caretPosition + 10)));

        // Find the word start
        int wordStart = caretPosition;
        while (wordStart > 0 && isWordChar(text.charAt(wordStart - 1))) {
            wordStart--;
        }

        String currentWord = text.substring(wordStart, caretPosition);
        System.out.printf("Current Word: '%s', Position: %d%n", currentWord, caretPosition);
        return currentWord;
    }

    /**
     * Checks if a character is part of a word.
     * 
     * @param c The character to check
     * @return True if the character is part of a word
     */
    private boolean isWordChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    /**
     * Generates autocompletion suggestions based on the current word.
     * 
     * @param currentWord The current word being typed
     * @return A list of suggestions
     */
    private List<String> generateSuggestions(String currentWord) {
        List<String> suggestions = new ArrayList<>();

        // Add language keywords
        if ("hsl".equals(language)) {
            // Add keywords from all categories
            addMatchingKeywords(suggestions, keywords.LANGUAGE_KEYWORDS.get("hsl"), currentWord);
            addMatchingKeywords(suggestions, keywords.SCOPE_KEYWORDS.get("hsl"), currentWord);
            addMatchingKeywords(suggestions, keywords.TYPES_KEYWORDS.get("hsl"), currentWord);
            addMatchingKeywords(suggestions, keywords.BLOCKS_KEYWORDS.get("hsl"), currentWord);

            // Add identifiers from the current file
            addMatchingIdentifiers(suggestions, currentWord);
        }

        // Sort suggestions alphabetically
        Collections.sort(suggestions);

        return suggestions;
    }

    /**
     * Adds matching keywords to the suggestions list.
     * 
     * @param suggestions The list to add suggestions to
     * @param keywords The list of keywords to check
     * @param prefix The prefix to match
     */
    private void addMatchingKeywords(List<String> suggestions, List<String> keywords, String prefix) {
        if (keywords != null) {
            for (String keyword : keywords) {
                if (keyword.startsWith(prefix) && !suggestions.contains(keyword)) {
                    System.out.printf("Keyword Found: '%s'%n", keyword);
                    suggestions.add(keyword);
                }
            }
        }
    }

    /**
     * Adds matching identifiers to the suggestions list.
     * 
     * @param suggestions The list to add suggestions to
     * @param prefix The prefix to match
     */
    private void addMatchingIdentifiers(List<String> suggestions, String prefix) {
        for (String identifier : identifiers) {
            if (identifier.startsWith(prefix) && !suggestions.contains(identifier)) {
                suggestions.add(identifier);
            }
        }
    }

    /**
     * Updates the list of identifiers in the current file.
     * 
     * @param text The current text in the code area
     */
    private void updateIdentifiers(String text) {
        identifiers.clear();
        System.out.println("Updating identifiers...");

        if ("hsl".equals(language)) {
            try {
                CharStream input = CharStreams.fromString(text);
                HslLexerLexer lexer = new HslLexerLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                tokens.fill(); // Collect tokens

                for (Token token : tokens.getTokens()) {
                    if (token.getType() == HslLexerLexer.ID_LEX) {
                        System.out.printf("Identifier Found: '%s'%n", token.getText());
                        identifiers.add(token.getText());
                    }
                }

                // Ensure identifiers from function/variable regex patterns are also captured
                extractFunctionNames(text);
                extractVariableDeclarations(text);
            } catch (Exception e) {
                System.err.println("Error updating identifiers: " + e.getMessage());
            }
        }
    }

    /**
     * Extracts function names from the text using regex.
     * 
     * @param text The text to extract function names from
     */
    private void extractFunctionNames(String text) {
        // Pattern to match function declarations: function name(...) { or function name(...)
        Pattern pattern = Pattern.compile("function\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            identifiers.add(matcher.group(1));
        }
    }

    /**
     * Extracts variable declarations from the text using regex.
     * 
     * @param text The text to extract variable declarations from
     */
    private void extractVariableDeclarations(String text) {
        // Pattern to match variable declarations: variable name = or variable name;
        Pattern pattern = Pattern.compile("variable\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*[=;]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            identifiers.add(matcher.group(1));
        }

        // Also match other variable types
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