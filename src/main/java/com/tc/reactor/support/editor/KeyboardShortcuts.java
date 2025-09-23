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
            } else if (new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN).match(event)) {
                shortcutFunctions.Save();
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
            mainView.logsTextArea.appendText("\n> CharBackTab shortcut pressed, but not implemented yet.");
        }

        public void CharTab() {
            codeArea.insertText(codeArea.getAbsolutePosition(codeArea.getCurrentParagraph(), 0), "\t");
        }

        public void ClearAllBookmarks() {
            mainView.logsTextArea.appendText("\n> ClearAlLBookmarks shortcut pressed, but not implemented yet.");}

        public void ClearSelection() { codeArea.deselect(); }

        public void CloseFileTab() {
            mainView.mainTabPane.getSelectionModel().getSelectedItem().getTabPane().getTabs().remove(mainView.mainTabPane.getSelectionModel().getSelectedItem());
        }

        public void CloseProject() { mainView.onCloseProjectClick(); }

        public void CloseWindow() {
            Platform.exit();
        }

        public void CollapseAllRegions() {
            mainView.logsTextArea.appendText("\n> CollapseAllRegions shortcut pressed, but not implemented yet.");}

        public void CollapseAllSubregions() {
            mainView.logsTextArea.appendText("\n> CollapseAllSubregions shortcut pressed, but not implemented yet.");}

        public void CollapseRegion() {
            mainView.logsTextArea.appendText("\n> CollapseRegion shortcut pressed, but not implemented yet.");}

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

        public void CreateNewFile() {
            mainView.logsTextArea.appendText("\n> CreateNewFile shortcut pressed, but not implemented yet.");}

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

        public void Find() {
            mainView.logsTextArea.appendText("\n> Find shortcut pressed, but not implemented yet.");}

        public void FindNext() {
            mainView.logsTextArea.appendText("\n> FindNext shortcut pressed, but not implemented yet.");}

        public void FindPrevious() {
            mainView.logsTextArea.appendText("\n> FindPrevious shortcut pressed, but not implemented yet.");}

        public void FindScope() {
            mainView.logsTextArea.appendText("\n> FindScope shortcut pressed, but not implemented yet.");}

        public void GoToLine() {
            mainView.logsTextArea.appendText("\n> GoToLine shortcut pressed, but not implemented yet.");}

        public void GoToSymbolDeclaration() {
            mainView.logsTextArea.appendText("\n> GoToSymbolDeclaration shortcut pressed, but not implemented yet.");}

        public void ImportDependencies() {
            mainView.logsTextArea.appendText("\n> ImportDependencies shortcut pressed, but not implemented yet.");}

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

        public void MoveToBlockEnd() {
            mainView.logsTextArea.appendText("\n> MoveToBlockEnd shortcut pressed, but not implemented yet.");}

        public void MoveToBlockStart() {
            mainView.logsTextArea.appendText("\n> MoveToBlockStart shortcut pressed, but not implemented yet.");}

        public void MoveToLineEnd() { codeArea.lineEnd(NavigationActions.SelectionPolicy.CLEAR); }

        public void MoveToLineStart() { codeArea.lineStart(NavigationActions.SelectionPolicy.CLEAR); }

        public void MoveToMethodEnd() { codeArea.end(NavigationActions.SelectionPolicy.CLEAR); }

        public void MoveToMethodStart() { codeArea.start(NavigationActions.SelectionPolicy.CLEAR); }

        public void MoveToNextBookmark() {
            mainView.logsTextArea.appendText("\n> MoveToNextBookmark shortcut pressed, but not implemented yet.");}

        public void MoveToPreviousBookmark() {
            mainView.logsTextArea.appendText("\n> MoveToPreviousBookmark shortcut pressed, but not implemented yet.");}

        public void MoveToWordEnd() {
            mainView.logsTextArea.appendText("\n> MoveToWordEnd shortcut pressed, but not implemented yet.");}

        public void MoveToWordStart() {
            mainView.logsTextArea.appendText("\n> MoveToWordStart shortcut pressed, but not implemented yet.");}

        public void NavigateToSpecificEditorTab() {
            mainView.logsTextArea.appendText("\n> NavigateToSpecificEditorTab shortcut pressed, but not implemented yet.");}

        public void NextFileTab() {
            mainView.mainTabPane.getSelectionModel().selectNext();
        }

        public void OpenDocumentation() {
            mainView.logsTextArea.appendText("\n> OpenDocumentation shortcut pressed, but not implemented yet.");}

        public void OpenRefactoringMenu() {
            mainView.logsTextArea.appendText("\n> OpenRefactoringMenu shortcut pressed, but not implemented yet.");}

        public void OpenScratchFile() {
            mainView.logsTextArea.appendText("\n> OpenScratchFile shortcut pressed, but not implemented yet.");}

        public void OpenSettings() {
            mainView.logsTextArea.appendText("\n> OpenSettings shortcut pressed, but not implemented yet.");}

        public void Paste() {
            codeArea.paste();
        }

        public void PreviousFileTab() {
            mainView.mainTabPane.getSelectionModel().selectPrevious();
        }

        public void Redo() {
            codeArea.redo();
        }

        public void RenameFile() {
            mainView.logsTextArea.appendText("\n> RenameFile shortcut pressed, but not implemented yet.");}

        public void Replace() {
            mainView.logsTextArea.appendText("\n> Replace shortcut pressed, but not implemented yet.");}

        public void Save() {
            mainView.saveCurrentFile();
        }

        public void ScrollDown() { codeArea.scrollYBy(codeArea.getLayoutBounds().getHeight() / 2);}

        public void ScrollUp() { codeArea.scrollYBy(-codeArea.getLayoutBounds().getHeight() / 2);}

        public void SearchInProject() {
            mainView.logsTextArea.appendText("\n> SearchInProject shortcut pressed, but not implemented yet.");}

        public void SelectAll() {
            codeArea.selectAll();
        }

        public void SelectionToLowercase() { codeArea.replaceSelection(codeArea.getSelectedText().toLowerCase()); }

        public void SelectionToUppercase() { codeArea.replaceSelection(codeArea.getSelectedText().toUpperCase()); }

        public void SelectWord() {
            codeArea.selectWord();
        }

        public void ShowAllSymbols() {
            mainView.logsTextArea.appendText("\n> ShowAllSymbols shortcut pressed, but not implemented yet.");}

        public void ToggleBookmark() {
            mainView.logsTextArea.appendText("\n> ToggleBookmark shortcut pressed, but not implemented yet.");}

        public void ToggleFindRegex() {
            mainView.logsTextArea.appendText("\n> ToggleFindRegex shortcut pressed, but not implemented yet.");}

        public void ToggleFullscreen() {
            mainView.logsTextArea.appendText("\n> ToggleFullscreen shortcut pressed, but not implemented yet.");}

        public void ToggleViewWhitespace() {
            mainView.logsTextArea.appendText("\n> ToggleViewWhitespace shortcut pressed, but not implemented yet.");}

        public void UncollapseAllRegions() {
            mainView.logsTextArea.appendText("\n> UncollapseAllRegions shortcut pressed, but not implemented yet.");}

        public void UncollapseAllSubregions() {
            mainView.logsTextArea.appendText("\n> UncollapseAllSubregions shortcut pressed, but not implemented yet.");}

        public void UncollapseRegion() {
            mainView.logsTextArea.appendText("\n> UncollapseRegion shortcut pressed, but not implemented yet.");}

        public void Undo() {
            codeArea.undo();
        }

        public void ZoomIn() {
            mainView.logsTextArea.appendText("\n> ZoomIn shortcut pressed, but not implemented yet.");}

        public void ZoomOut() {
            mainView.logsTextArea.appendText("\n> ZoomOut shortcut pressed, but not implemented yet.");}

    }

}
