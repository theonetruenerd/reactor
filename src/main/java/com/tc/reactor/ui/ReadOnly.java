package com.tc.reactor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.stage.Stage;

public class ReadOnly {

    @FXML
    ButtonBar readOnlyButtonBar;

    @FXML
    private void onCancelClicked() {
        Stage stage = (Stage) readOnlyButtonBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onNoReadOnlyClicked() {
        Stage stage = (Stage) readOnlyButtonBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onYesReadOnlyClicked() {
        Stage stage = (Stage) readOnlyButtonBar.getScene().getWindow();
        stage.close();
    }
}
