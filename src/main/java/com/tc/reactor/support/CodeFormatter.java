package com.tc.reactor.support;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeFormatter {

    /**
     * Sets up automatic formatting for the given language.
     */
    public void setupAutoFormatting(CodeArea codeArea, String language) {
        codeArea.setOnKeyTyped(event -> {
            String typedChar = event.getCharacter();

            // Check if language-specific formatting is needed
            if ("hsl".equals(language)) {
                switch (typedChar) {
                    case "{":
                        autoAddBraces(codeArea);
                        break;
                    case "(":
                        autoAddParentheses(codeArea);
                        break;
                    case "[":
                        autoAddSquareBrackets(codeArea);
                        break;
                    case "\"":
                        autoAddDoubleQuotes(codeArea);
                        break;
                    case "'":
                        autoAddSingleQuotes(codeArea);
                        break;
                    case "\\":
                        int caretPosition = codeArea.getCaretPosition();
                        if (codeArea.getText(caretPosition - 1, caretPosition).equals("\\")) {
                            autoAddDocustrings(codeArea);
                        }
                }
            }
        });
        codeArea.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    autoIndent(codeArea, language);
                    break;
                default:
                    break;
            };
        });
    }

    private void autoAddDocustrings(CodeArea codeArea) {
        int caretPosition = codeArea.getCaretPosition();
        codeArea.insertText(caretPosition, "\n\\\\ Function: \n\\\\ Scope: \n\\\\ Description: \n\\\\ Parameters: \n\\\\ Returns:");
        codeArea.moveTo(caretPosition);
    }

    /**
     * Automatically adds a single quotation mark (`'`) at the current caret position
     * and repositions the caret back to its original location.
     *
     * @param codeArea The CodeArea where the single quote should be added.
     */
    private void autoAddSingleQuotes(CodeArea codeArea) {
        int caretPosition = codeArea.getCaretPosition();
        int startPosition = codeArea.getSelection().getStart();
        int endPosition = codeArea.getSelection().getEnd();
        if (endPosition == startPosition) {
            codeArea.insertText(caretPosition, "'");
            codeArea.moveTo(caretPosition);
        } else
        {
            String selectedText = codeArea.getText(startPosition, endPosition);
            codeArea.replaceText(startPosition, endPosition, "'" + selectedText + "'");
        }

    }

    /**
     * Automatically adds a double quotation mark (`"`) at the current caret position
     * and repositions the caret back to its original location.
     *
     * @param codeArea The CodeArea where the double quotation mark should be added.
     */
    private void autoAddDoubleQuotes(CodeArea codeArea) {
        int caretPosition = codeArea.getCaretPosition();
        int startPosition = codeArea.getSelection().getStart();
        int endPosition = codeArea.getSelection().getEnd();
        if (endPosition == startPosition) {
            codeArea.insertText(caretPosition, "\"");
            codeArea.moveTo(caretPosition);
        } else
        {
            String selectedText = codeArea.getText(startPosition, endPosition);
            codeArea.replaceText(startPosition, endPosition, "'" + selectedText + "'");
        }
    }

    /**
     * Automatically adds a closing square bracket ']' at the current caret position
     * and repositions the caret back to its original location.
     *
     * @param codeArea The CodeArea where the square bracket should be added.
     */
    private void autoAddSquareBrackets(CodeArea codeArea) {
        int caretPosition = codeArea.getCaretPosition();
        int startPosition = codeArea.getSelection().getStart();
        int endPosition = codeArea.getSelection().getEnd();
        if (endPosition == startPosition) {
            codeArea.insertText(caretPosition, "]");
            codeArea.moveTo(caretPosition);
        } else
        {
            String selectedText = codeArea.getText(startPosition, endPosition);
            codeArea.replaceText(startPosition, endPosition, "'" + selectedText + "'");
        }
    }

    /**
     * Automatically adds a closing parenthesis at the current caret position
     * and repositions the caret within the parentheses.
     *
     * @param codeArea The CodeArea where the parenthesis should be added.
     */
    private void autoAddParentheses(CodeArea codeArea) {
        int caretPosition = codeArea.getCaretPosition();
        int startPosition = codeArea.getSelection().getStart();
        int endPosition = codeArea.getSelection().getEnd();
        if (endPosition == startPosition) {
            codeArea.insertText(caretPosition, ")");
            codeArea.moveTo(caretPosition);
        } else
        {
            String selectedText = codeArea.getText(startPosition, endPosition);
            codeArea.replaceText(startPosition, endPosition, "'" + selectedText + "'");
        }
    }

    /**
     * Automatically adds closing brace '}' with proper indentation.
     */
    private void autoAddBraces(CodeArea codeArea) {
        int caretPosition = codeArea.getCaretPosition();
        int startPosition = codeArea.getSelection().getStart();
        int endPosition = codeArea.getSelection().getEnd();


        if (endPosition == startPosition) {
            // Get the current line where the user typed '{'
            int currentParagraph = codeArea.getCurrentParagraph();
            String currentLine = codeArea.getParagraph(currentParagraph).getText();

            // Calculate the indentation for this line
            String indentation = getLineIndentation(currentLine);

            // Insert a properly formatted block with braces
            String bracesBlock = "\n" + indentation + "    \n" + indentation + "}";
            codeArea.insertText(caretPosition, bracesBlock);

            // Move the caret inside the braces
            codeArea.moveTo(caretPosition + indentation.length() + 5); // Place inside the block
        } else
        {
            String selectedText = codeArea.getText(startPosition, endPosition);
            codeArea.replaceText(startPosition, endPosition, "'" + selectedText + "'");
        }


    }

    /**
     * Extracts the leading whitespace (indentation) of a given line.
     *
     * @param line The line of text to analyze.
     * @return A string containing the leading spaces or tabs of the line.
     */
    private String getLineIndentation(String line) {
        int i = 0;
        while (i < line.length() && Character.isWhitespace(line.charAt(i))) {
            i++;
        }
        return line.substring(0, i);
    }

    /**
     * Automatically indents code based on the current syntax and context.
     */
    private void autoIndent(CodeArea codeArea, String language) {
        // Get the caret position and line content
        int caretPosition = codeArea.getCaretPosition();
        int currentParagraph = codeArea.getCurrentParagraph();
        String previousLine = currentParagraph > 0 ? codeArea.getParagraph(currentParagraph - 1).getText() : "";

        // Calculate indentation level based on the language
        if ("hsl".equals(language)) {
            int indentLevel = calculateIndentation(previousLine);
            String indent = " ".repeat(indentLevel); // Use 4 spaces per indentation
            codeArea.insertText(caretPosition, indent);
        }
    }

    /**
     * Calculates the required indentation level for the next line based on the previous line.
     */
    private int calculateIndentation(String previousLine) {
        int level = getLineIndentation(previousLine).length();

        System.out.println("previousLine: " + previousLine);
        System.out.println("level: " + level);

        // Match any opening/closing braces
        Matcher openBraceMatcher = Pattern.compile("\u0092").matcher(previousLine);
        Matcher closeBraceMatcher = Pattern.compile("\u0094").matcher(previousLine);

        while (openBraceMatcher.find()) {
            level = level + 4;
        }
        while (closeBraceMatcher.find()) {
            level = level - 4;
        }

        return Math.max(level, 0); // Ensure indentation level is non-negative
    }
}