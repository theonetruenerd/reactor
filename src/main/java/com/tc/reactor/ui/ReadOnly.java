package com.tc.reactor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReadOnly {
    @FXML private Button yesButton;
    @FXML private Button noButton;

    private boolean isReadOnly = false;

    @FXML
    private void onYesButtonClicked() {
        isReadOnly = true;
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onNoButtonClicked() {
        isReadOnly = false;
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }
}
