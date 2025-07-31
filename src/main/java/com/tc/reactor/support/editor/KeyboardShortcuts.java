package com.tc.reactor.support.editor;

import com.tc.reactor.ui.MainView;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.CodeArea;

public class KeyboardShortcuts {

    private MainView mainView;
    private CodeArea codeArea;

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public void setCodeArea(CodeArea codeArea) {
        this.codeArea = codeArea;
    }

    ShortcutFunctions shortcutFunctions = new ShortcutFunctions();

    public void SetupShortcuts (){
        codeArea.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            if (new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN).match(event)) {
                mainView.logsTextArea.appendText("\n> File save keybind pressed");
                shortcutFunctions.Save();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN).match(event)) {
                mainView.logsTextArea.appendText("\n> Next file tab keybind pressed");
                shortcutFunctions.NextFileTab();
            } else if (new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                mainView.logsTextArea.appendText("\n> Previous file tab keybind pressed");
                shortcutFunctions.PreviousFileTab();
            } else if (new KeyCodeCombination(KeyCode.BACK_SLASH, KeyCombination.CONTROL_DOWN).match(event)) {
                mainView.logsTextArea.appendText("\n> Comment line keybind pressed");
                shortcutFunctions.CommentLine();
            } else if (new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN).match(event)) {
                mainView.logsTextArea.appendText("\n> Close tab keybind pressed");
                shortcutFunctions.CloseFileTab();
            } else if (new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                mainView.logsTextArea.appendText("\n> Delete line keybind pressed");
                shortcutFunctions.DeleteLine();
            }
        });
    }

    private class ShortcutFunctions {

        public void CharBackTab() {}

        public void CharTab() {}

        public void ClearAllBookmarks() {}

        public void ClearSelection() {}

        public void CloseFileTab() {
            mainView.mainTabPane.getSelectionModel().getSelectedItem().getTabPane().getTabs().remove(mainView.mainTabPane.getSelectionModel().getSelectedItem());
        }

        public void CloseProject() {}

        public void CloseWindow() {
            Platform.exit();
        }

        public void CollapseAllRegions() {}

        public void CollapseAllSubregions() {}

        public void CollapseRegion() {}

        public void CommentLine() {
            // Currently assumes tab name doesn't have a dot in it other than for file extension; perhaps an easier / more
            // reliable way to grab extension exists
            String extension = mainView.mainTabPane.getSelectionModel().getSelectedItem().getText().split("\\.")[1];
            switch (extension) {  // Other cases need adding when language support gets added
                case "hsl":
                    codeArea.insertText(codeArea.getAbsolutePosition(codeArea.getCurrentParagraph(), 0),"//");
                    break;
                default:  // Doesn't do anything if the language isn't coded in here to avoid accidentally messing up files
                    break;
            }
        }

        public void Copy() {
            codeArea.copy();
        }

        public void CreateNewFile() {}

        public void Cut() {
            codeArea.cut();
        }

        public void CutLine() {
            codeArea.selectLine();
            codeArea.cut();
            codeArea.deselect();
        }

        public void DeleteLine() {
            codeArea.deleteText(codeArea.getCurrentParagraph(), codeArea.getCurrentParagraph() + 1);
        }

        public void DuplicateLine() {
            String line = codeArea.getText(codeArea.getCurrentParagraph());
            codeArea.insertText(codeArea.getAbsolutePosition(codeArea.getCurrentParagraph(), 0), line + "\n");
        }

        public void Find() {}

        public void FindNext() {}

        public void FindPrevious() {}

        public void FindScope() {}

        public void GoToLine() {}

        public void GoToSymbolDeclaration() {}

        public void ImportDependencies() {}

        public void IndentSelection() {}

        public void MoveLineDown() {}

        public void MoveLineUp() {}

        public void MoveToBlockEnd() {}

        public void MoveToBlockStart() {}

        public void MoveToLineEnd() {}

        public void MoveToLineStart() {}

        public void MoveToMethodEnd() {}

        public void MoveToMethodStart() {}

        public void MoveToNextBookmark() {}

        public void MoveToPreviousBookmark() {}

        public void MoveToWordEnd() {}

        public void MoveToWordStart() {}

        public void NavigateToSpecificEditorTab() {}

        public void NextFileTab() {
            mainView.mainTabPane.getSelectionModel().selectNext();
        }

        public void OpenDocumentation() {}

        public void OpenRefactoringMenu() {}

        public void OpenScratchFile() {}

        public void OpenSettings() {}

        public void Paste() {
            codeArea.paste();
        }

        public void PreviousFileTab() {
            mainView.mainTabPane.getSelectionModel().selectPrevious();
        }

        public void Redo() {
            codeArea.redo();
        }

        public void RenameFile() {}

        public void Replace() {}

        public void Save() {}

        public void ScrollDown() {}

        public void ScrollUp() {}

        public void SearchInProject() {}

        public void SelectAll() {
            codeArea.selectAll();
        }

        public void SelectionToLowercase() {}

        public void SelectionToUppercase() {}

        public void SelectWord() {
            codeArea.selectWord();
        }

        public void ShowAllSymbols() {}

        public void ToggleBookmark() {}

        public void ToggleFindRegex() {}

        public void ToggleFullscreen() {}

        public void ToggleInsert() {}

        public void ToggleViewWhitespace() { }

        public void UncollapseAllRegions() {}

        public void UncollapseAllSubregions() {}

        public void UncollapseRegion() {}

        public void Undo() {
            codeArea.undo();
        }

        public void UnindentSelection() {}

        public void ZoomIn() {}

        public void ZoomOut() {}

    }

}
