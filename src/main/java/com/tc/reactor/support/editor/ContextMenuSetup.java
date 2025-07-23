package com.tc.reactor.support.editor;

import com.tc.reactor.ui.MainView;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.fxmisc.richtext.CodeArea;

public class ContextMenuSetup {

    private MainView mainView;

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public void setupContextMenu(CodeArea codeArea) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem renameVariableItem = new MenuItem("Rename Variable");
        renameVariableItem.setOnAction(event -> {
            Refactoring refactoring = new Refactoring(codeArea);
            refactoring.setMainView(mainView);
            refactoring.renameVariable(codeArea);
        });

        contextMenu.getItems().addAll(renameVariableItem);

        codeArea.setContextMenu(contextMenu);
    }
}
