package com.tc.reactor.ui;

public class ReadOnly {
    @FXML private Button yesButton;
    @FXML private Button noButton;

    private boolean isReadOnly = false;

    @FXML
    private void onYesButtonClicked() {
        isReadOnly = true;
        yesButton.setDisable(true);
        noButton.setDisable(true);
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onNoButtonClicked() {
        isReadOnly = false;
        yesButton.setDisable(false);
        noButton.setDisable(false);
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
        // test
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }
}
