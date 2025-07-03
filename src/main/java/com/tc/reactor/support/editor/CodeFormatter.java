package com.tc.reactor.support.editor;

import org.fxmisc.richtext.CodeArea;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeFormatter {

    // TODO Fix the selected-text-flanking replacement

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
                    case "/":
                        int caretPosition = codeArea.getCaretPosition();
                        if (codeArea.getText(caretPosition - 2, caretPosition - 1).equals("/")) {
                            autoAddDocustrings(codeArea, language);
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

    // TODO Grab variables etc dynamically
    private void autoAddDocustrings(CodeArea codeArea, String language) {
        int caretPosition = codeArea.getCaretPosition();
        String indentation = getLineIndentation(codeArea.getParagraph(codeArea.getCurrentParagraph()).getText());
        int currentParagraphIndex = codeArea.getCurrentParagraph();
        String nextLine = "";
        if (currentParagraphIndex + 1 < codeArea.getParagraphs().size()) {
            nextLine = codeArea.getParagraph(currentParagraphIndex + 1).getText();
        }
        switch (language) {
            case "hsl":
                if (!nextLine.isEmpty()) {
                    System.out.println("Next Line: " + nextLine);
                    if (nextLine.contains("function")) {

                        String scope = "";
                        // Scope
                        if (nextLine.startsWith("private")) {
                            scope = "private";
                        } else if (nextLine.startsWith("global")) {
                            scope = "global";
                        } else if (nextLine.startsWith("static")) {
                            scope = "static";
                        } else {
                            scope = "public";
                        }

                        // Function name
                        String functionName = nextLine.substring(nextLine.indexOf("function") + 8, nextLine.indexOf("("));

                        // Description
                        String description = "";

                        // Parameters
                        String[] parameters = nextLine.substring(nextLine.indexOf("(") + 1, nextLine.indexOf(")")).split(",");

                        // Return type
                        String returnType = "";
                        if (!nextLine.endsWith(")")) {
                            returnType = nextLine.substring(nextLine.indexOf(")") + 1, nextLine.indexOf("{"));
                        }

                        String indent = getLineIndentation(nextLine);
                        StringBuilder docstring = createDocstring(functionName, scope, description, parameters, returnType, indent);

                        codeArea.insertText(caretPosition, docstring.toString());

                        return;
                    }
                }
                codeArea.insertText(caretPosition, " Function: \n"+indentation+"// Scope: \n"+indentation+"// Description: \n"+indentation+"// Parameters: \n"+indentation+"// Returns:");
                codeArea.moveTo(caretPosition + 11);
        }
    }

    private StringBuilder createDocstring(String function, String scope, String description, String[] parameters, String returns, String indent) {
        StringBuilder docstring = new StringBuilder();
        docstring.append("--------------------------\n");
        docstring.append(indent).append("// Function: ").append(function).append("\n");
        docstring.append(indent).append("// Scope: ").append(scope).append("\n");
        docstring.append(indent).append("// Description: ").append(description).append("\n");
        docstring.append(indent).append("// Parameters: \n");
        for (String parameter : parameters) {
            docstring.append(indent).append("//    ");
            if (parameter.startsWith("i")) {
                docstring.append(indent).append("[i] ");
            } else if (parameter.startsWith("o")) {
                docstring.append(indent).append("[o] ");
            }
            docstring.append(indent).append(parameter).append("\n");
        }
        docstring.append(indent).append("// Returns: ").append(returns).append("\n");
        docstring.append(indent).append("//--------------------------");
        return docstring;
    }

    /**
     * Automatically adds a single quotation mark (`'`) at the current caret position
     * and repositions the caret back to its original location.
     *
     * @param codeArea The CodeArea where the single quote should be added.
     */
    private void autoAddSingleQuotes(CodeArea codeArea) {
        int caretPosition = codeArea.getCaretPosition();
        int startPosition = codeArea.getAnchor();
        System.out.println("Start Position: " + startPosition);
        System.out.println("Caret Position: " + caretPosition);
        if (caretPosition == startPosition) {
            codeArea.insertText(caretPosition, "'");
            codeArea.moveTo(caretPosition);
        } else
        {
            String selectedText = codeArea.getText(startPosition, caretPosition);
            System.out.println("Selected Text: " + selectedText);
            codeArea.replaceText(startPosition, caretPosition, "'" + selectedText + "'");
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
        int startPosition = codeArea.getAnchor();
        String selectedText = codeArea.getSelectedText();
        String selection = codeArea.getSelection().toString();
        System.out.println("Selected Text: " + selectedText);
        System.out.println("Selection: " + selection);
        System.out.println("Start Position: " + startPosition);
        System.out.println("Caret Position: " + caretPosition);
        if (caretPosition == startPosition) {
            codeArea.insertText(caretPosition, "\"");
            codeArea.moveTo(caretPosition);
        } else
        {
            String selectedText2 = codeArea.getText(startPosition, caretPosition);
            System.out.println("Selected Text: " + selectedText2);
            codeArea.replaceText(startPosition, caretPosition, "\"" + selectedText2 + "\"");
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