package com.tc.reactor.support.editor;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.fxmisc.richtext.CodeArea;

public class ContextMenuSetup {

    public void setupContextMenu(CodeArea codeArea) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem renameVariableItem = new MenuItem("Rename Variable");
        renameVariableItem.setOnAction(event -> {
            Refactoring refactoring = new Refactoring(codeArea);
            refactoring.renameVariable(codeArea);
        });

        contextMenu.getItems().addAll(renameVariableItem);

        codeArea.setContextMenu(contextMenu);
    }
}
