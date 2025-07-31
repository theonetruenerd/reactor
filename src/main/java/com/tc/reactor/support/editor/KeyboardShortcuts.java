package com.tc.reactor.support.editor;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.CodeArea;

public class KeyboardShortcuts {

    ShortcutFunctions shortcutFunctions = new ShortcutFunctions();

    public void SetupShortcuts (CodeArea codeArea){
        codeArea.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            if (new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Save();
                event.consume();
            }
        });
    }

    private static class ShortcutFunctions {

        public void Save() { }

        public void ToggleViewWhitespace() { }

        public void SelectAll() {}

        public void Copy() {}

        public void Paste() {}

        public void Cut() {}

        public void DeleteLine() {}

        public void CommentLine() {}

        public void Find() {}

        public void Replace() {}

        public void ChangeTabs() {}

        public void SearchInProject() {}

        public void CutLine() {}

        public void SelectionToUppercase() {}

        public void SelectionToLowercase() {}

        public void Undo() {}

        public void Redo() {}

        public void ScrollDown() {}

        public void ScrollUp() {}

        public void MoveToLineEnd() {}

        public void MoveToBlockEnd() {}

        public void MoveToMethodEnd() {}

        public void MoveToLineStart() {}

        public void MoveToBlockStart() {}

        public void MoveToMethodStart() {}

        public void ClearSelection() {}

        public void ToggleBookmark() {}

        public void MoveToPreviousBookmark() {}

        public void MoveToNextBookmark() {}

        public void ClearAllBookmarks() {}

        public void FindNext() {}

        public void FindPrevious() {}

        public void ToggleInsert() {}

        public void MoveToWordStart() {}

        public void MoveToWordEnd() {}

        public void IndentSelection() {}

        public void UnindentSelection() {}

        public void SelectWord() {}

        public void CharBackTab() {}

        public void CharTab() {}

        public void FindScope() {}

        public void MoveLineDown() {}

        public void MoveLineUp() {}

        public void CreateNewFile() {}

        public void OpenRefactoringMenu() {}

        public void ImportDependencies() {}

        public void DuplicateLine() {}

        public void NextFileTab() {}

        public void PreviousFileTab() {}

        public void OpenSettings() {}

        public void CloseFileTab() {}

        public void CloseWindow() {}

        public void OpenDocumentation() {}

        public void CollapseRegion() {}

        public void UncollapseRegion() {}

        public void CollapseAllRegions() {}

        public void UncollapseAllRegions() {}

        public void CollapseAllSubregions() {}

        public void UncollapseAllSubregions() {}

        public void ShowAllSymbols() {}

        public void GoToLine() {}

        public void GoToSymbolDeclaration() {}

        public void ToggleFindRegex() {}

        public void RenameFile() {}

        public void NavigateToSpecificEditorTab() {}

        public void ToggleFullscreen() {}

        public void ZoomIn() {}

        public void ZoomOut() {}

        public void OpenScratchFile() {}

    }

}
