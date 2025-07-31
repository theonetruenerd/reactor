package com.tc.reactor.support.editor;

import com.tc.reactor.ui.MainView;
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
                shortcutFunctions.Save();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.NextFileTab();
            } else if (new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.PreviousFileTab();
            } else if (new KeyCodeCombination(KeyCode.BACK_SLASH, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.CommentLine();
            } else if (new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.CloseFileTab();
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

        public void CloseWindow() {}

        public void CollapseAllRegions() {}

        public void CollapseAllSubregions() {}

        public void CollapseRegion() {}

        public void CommentLine() {
            // Currently assumes tab name doesn't have a dot in it other than for file extension; perhaps easier/more
            // reliable way to grab extension exists
            String extension = mainView.mainTabPane.getSelectionModel().getSelectedItem().getText().split("\\.")[1];
            switch (extension) {  // Other cases need adding when language support gets added
                case "hsl":
                    codeArea.insertText(codeArea.getAbsolutePosition(codeArea.getCurrentParagraph(), 0),"//");
                    break;
                default:  // Doesn't do anything if language isn't coded in here to avoid accidentally messing up files
                    break;
            }
        }

        public void Copy() {}

        public void CreateNewFile() {}

        public void Cut() {}

        public void CutLine() {}

        public void DeleteLine() {}

        public void DuplicateLine() {}

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

        public void Paste() {}

        public void PreviousFileTab() {
            mainView.mainTabPane.getSelectionModel().selectPrevious();
        }

        public void Redo() {}

        public void RenameFile() {}

        public void Replace() {}

        public void Save() { }

        public void ScrollDown() {}

        public void ScrollUp() {}

        public void SearchInProject() {}

        public void SelectAll() {}

        public void SelectionToLowercase() {}

        public void SelectionToUppercase() {}

        public void SelectWord() {}

        public void ShowAllSymbols() {}

        public void ToggleBookmark() {}

        public void ToggleFindRegex() {}

        public void ToggleFullscreen() {}

        public void ToggleInsert() {}

        public void ToggleViewWhitespace() { }

        public void UncollapseAllRegions() {}

        public void UncollapseAllSubregions() {}

        public void UncollapseRegion() {}

        public void Undo() {}

        public void UnindentSelection() {}

        public void ZoomIn() {}

        public void ZoomOut() {}

    }

}
