package com.tc.reactor.support.editor;

import com.tc.reactor.ui.MainView;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.NavigationActions;

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

            if (new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.NextFileTab();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.PreviousFileTab();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.BACK_SLASH, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.CommentLine();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.DeleteLine();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Find();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Copy();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Paste();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Cut();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Undo();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Redo();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.SelectAll();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.DuplicateLine();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.CutLine();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.CloseProject();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.CloseFileTab();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.CloseWindow();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.CharBackTab();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.CharTab();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.F11, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.ClearAllBookmarks();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.ESCAPE, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.ClearSelection();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.CollapseRegion();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.CollapseAllRegions();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.PLUS, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.UncollapseRegion();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.PLUS, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.UncollapseAllRegions();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.CollapseAllSubregions();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.PLUS, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.UncollapseAllSubregions();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.CreateNewFile();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.F3).match(event)) {
                shortcutFunctions.FindNext();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.F3, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.FindPrevious();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.FindScope();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.GoToLine();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.ToggleBookmark();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.OpenRefactoringMenu();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.OpenDocumentation();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.ImportDependencies();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.UP, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.MoveLineUp();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.DOWN, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.MoveLineDown();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.MoveToBlockStart();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.MoveToBlockEnd();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.MoveToLineStart();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.MoveToLineEnd();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.END, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.MoveToMethodEnd();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.END, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.MoveToMethodStart();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.MoveToNextBookmark();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.MoveToPreviousBookmark();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.MoveToWordEnd();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.LEFT, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.MoveToWordStart();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.NavigateToSpecificEditorTab();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.OpenSettings();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.SearchInProject();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.OpenScratchFile();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.RenameFile();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Replace();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.PAGE_UP, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.ScrollUp();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.PAGE_DOWN, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.ScrollDown();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.U, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.SelectionToUppercase();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.U, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.SelectionToLowercase();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.K, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.SelectWord();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.K, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN).match(event)) {
                shortcutFunctions.ShowAllSymbols();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.K, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.GoToSymbolDeclaration();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.F12).match(event)) {
                shortcutFunctions.ToggleFullscreen();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.ToggleViewWhitespace();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.ToggleFindRegex();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.PLUS, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.ZoomIn();
                event.consume();
            } else if (new KeyCodeCombination(KeyCode.MINUS, KeyCombination.ALT_DOWN).match(event)) {
                shortcutFunctions.ZoomOut();
                event.consume();
            }
        });
    }

    private class ShortcutFunctions {

        public void CharBackTab() {

        }

        public void CharTab() {
            codeArea.insertText(codeArea.getAbsolutePosition(codeArea.getCurrentParagraph(), 0), "\t");
        }

        public void ClearAllBookmarks() {}

        public void ClearSelection() { codeArea.deselect(); }

        public void CloseFileTab() {
            mainView.mainTabPane.getSelectionModel().getSelectedItem().getTabPane().getTabs().remove(mainView.mainTabPane.getSelectionModel().getSelectedItem());
        }

        public void CloseProject() { mainView.onCloseProjectClick(); }

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

        public void CreateNewFile() { }

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

        public void MoveLineDown() {
            codeArea.selectLine();
            codeArea.cut();
            codeArea.moveTo(codeArea.getCurrentParagraph() + 1, 0);
            codeArea.paste();
        }

        public void MoveLineUp() {
            codeArea.selectLine();
            codeArea.cut();
            codeArea.moveTo(codeArea.getCurrentParagraph() - 1, 0);
            codeArea.paste();
        }

        public void MoveToBlockEnd() {}

        public void MoveToBlockStart() {}

        public void MoveToLineEnd() { codeArea.lineEnd(NavigationActions.SelectionPolicy.CLEAR); }

        public void MoveToLineStart() { codeArea.lineStart(NavigationActions.SelectionPolicy.CLEAR); }

        public void MoveToMethodEnd() { codeArea.end(NavigationActions.SelectionPolicy.CLEAR); }

        public void MoveToMethodStart() { codeArea.start(NavigationActions.SelectionPolicy.CLEAR); }

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

        public void ScrollDown() { codeArea.scrollYBy(codeArea.getLayoutBounds().getHeight() / 2);}

        public void ScrollUp() { codeArea.scrollYBy(-codeArea.getLayoutBounds().getHeight() / 2);}

        public void SearchInProject() {}

        public void SelectAll() {
            codeArea.selectAll();
        }

        public void SelectionToLowercase() { codeArea.replaceSelection(codeArea.getSelectedText().toLowerCase()); }

        public void SelectionToUppercase() { codeArea.replaceSelection(codeArea.getSelectedText().toUpperCase()); }

        public void SelectWord() {
            codeArea.selectWord();
        }

        public void ShowAllSymbols() {}

        public void ToggleBookmark() {}

        public void ToggleFindRegex() {}

        public void ToggleFullscreen() {}

        public void ToggleViewWhitespace() { }

        public void UncollapseAllRegions() {}

        public void UncollapseAllSubregions() {}

        public void UncollapseRegion() {}

        public void Undo() {
            codeArea.undo();
        }

        public void ZoomIn() {}

        public void ZoomOut() {}

    }

}
