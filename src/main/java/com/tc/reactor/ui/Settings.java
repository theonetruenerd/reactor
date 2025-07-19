package com.tc.reactor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Settings {

    @FXML private MenuBar settingsMenuBar;
    @FXML private ButtonBar settingsButtonBar;
    @FXML private Button settingsApplyButton;
    @FXML private Button settingsCancelButton;
    @FXML private ListView<String> settingsListView;

    @FXML
    private void initialize() {

    }

    private void applySettings() {

    }

    private void cancelSettings() {
        Stage stage = (Stage) settingsButtonBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSettingsApplyButtonClick() {
        applySettings();
    }

    @FXML
    private void onSettingsCancelButtonClick() {
        cancelSettings();
    }

}
