package com.tc.reactor.support.editor;

import java.util.List;
import java.util.regex.Pattern;

import javafx.scene.control.TextInputDialog;
import org.fxmisc.richtext.CodeArea;

public class Refactoring {

    private final CodeArea codeArea;

    public Refactoring(CodeArea codeArea) {
        this.codeArea = codeArea;
    }

    public void renameVariable(CodeArea codeArea) {
        // Step 1: Identify the selected variable
        String oldName = getSelectedVariableName();

        if (oldName.isEmpty()) {
            System.out.println("No variable selected for renaming.");
            return;
        }

        // Step 2: Prompt the user for a new name
        String newName = promptForNewVariableName(oldName);

        if (newName == null || newName.trim().isEmpty() || oldName.equals(newName)) {
            System.out.println("Rename canceled or new name is invalid.");
            return;
        }

        // Step 3: Update all occurrences of the variable in the file
        renameVariableInFile(codeArea, oldName, newName);
    }


    private void renameVariableInFile(CodeArea codeArea, String oldName, String newName) {
        // Regex to match the exact variable name
        String regex = "\\b" + Pattern.quote(oldName) + "\\b";

        // Replace all occurrences of the variable
        String updatedText = codeArea.getText().replaceAll(regex, newName);

        // Update the editor with the modified text
        codeArea.replaceText(updatedText);

        System.out.println("Renamed all occurrences of '" + oldName + "' to '" + newName + "'.");
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

}
