package com.tc.reactor.support.editor;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tc.reactor.ui.MainView;
import javafx.scene.control.TextInputDialog;
import org.fxmisc.richtext.CodeArea;

public class Refactoring {

    private MainView mainView;

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    private final CodeArea codeArea;
    private final Set<String> identifiers = new HashSet<>();

    public Refactoring(CodeArea codeArea) {
        this.codeArea = codeArea;
    }

    public void renameVariable(CodeArea codeArea) {
        String oldName = getSelectedVariableName();

        extractVariableDeclarations(codeArea.getText());

        if (!identifiers.contains(oldName)) {
            mainView.logsTextArea.appendText("\n> No variable with name '" + oldName + "' found in the file.");
            mainView.bottomTabPane.getSelectionModel().select(mainView.logTab);
            return;
        }

        if (oldName.isEmpty()) {
            mainView.logsTextArea.appendText("\n> No variable selected for renaming.");
            mainView.bottomTabPane.getSelectionModel().select(mainView.logTab);
            return;
        }

        String newName = promptForNewVariableName(oldName);

        if (newName == null || newName.trim().isEmpty() || oldName.equals(newName)) {
            mainView.logsTextArea.appendText("\n> Rename canceled or new name is invalid.");
            mainView.bottomTabPane.getSelectionModel().select(mainView.logTab);
            return;
        }

        renameVariableInFile(codeArea, oldName, newName);
    }


    private void renameVariableInFile(CodeArea codeArea, String oldName, String newName) {
        // Regex to match the exact variable name
        String regex = "\\b" + Pattern.quote(oldName) + "\\b";

        // Replace all occurrences of the variable
        String updatedText = codeArea.getText().replaceAll(regex, newName);

        // Update the editor with the modified text
        codeArea.replaceText(updatedText);

        mainView.logsTextArea.appendText("\n> Renamed all occurrences of '" + oldName + "' to '" + newName + "'.");
        mainView.bottomTabPane.getSelectionModel().select(mainView.logTab);
    }


    private String getSelectedVariableName() {
        String selectedText = codeArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            return selectedText;
        } else {
            int caretPosition = codeArea.getCaretPosition();
            String text = codeArea.getText();
            int wordStart = caretPosition;
            int wordEnd = caretPosition;
            while (wordStart > 0 && Character.isLetterOrDigit(text.charAt(wordStart - 1))) {
                wordStart--;
            }
            while (wordEnd < text.length() && Character.isLetterOrDigit(text.charAt(wordEnd))) {
                wordEnd++;
            }
            return text.substring(wordStart, wordEnd);
        }
    }

    private String promptForNewVariableName(String oldName) {
        TextInputDialog dialog = new TextInputDialog(oldName);
        dialog.setTitle("Rename Variable");
        dialog.setHeaderText("Rename Variable");
        dialog.setContentText("Enter the new name for the variable '" + oldName + "':");

        return dialog.showAndWait().orElse(null); // Returns the new name, or null if the dialog is canceled
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
