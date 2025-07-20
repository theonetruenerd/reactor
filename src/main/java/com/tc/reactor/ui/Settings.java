package com.tc.reactor.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settings {

    @FXML private MenuBar settingsMenuBar;
    @FXML private ButtonBar settingsButtonBar;
    @FXML private Button settingsApplyButton;
    @FXML private Button settingsCancelButton;
    @FXML private ListView<String> settingsGroupsList;
    @FXML private StackPane settingsStackPane;

    @FXML
    private void initialize() {
        setupSettingsTabs();

        settingsGroupsList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal != null && !newVal.isBlank() && !newVal.equals(oldVal)) {
                loadSettingsContent(newVal);
            }
        });

        if (!settingsGroupsList.getItems().isEmpty()) {
            settingsGroupsList.getSelectionModel().selectFirst();
        }
    }

    private void loadSettingsContent(String settingsGroup) {
        settingsStackPane.getChildren().clear();

        switch (settingsGroup) {
            case "Appearance and Behaviour":
                settingsStackPane.getChildren().add(createAppearanceAndBehaviourSettings());
                break;
            case "Keybinds":
                settingsStackPane.getChildren().add(createKeybindSettings());
                break;
            case "Editor":
                settingsStackPane.getChildren().add(createEditorSettings());
                break;
            case "Version Control":
                settingsStackPane.getChildren().add(createVersionControlSettings());
                break;
            case "Languages":
                settingsStackPane.getChildren().add(createLanguagesSettings());
                break;
            case "Tools":
                settingsStackPane.getChildren().add(createToolsSettings());
                break;
            case "Backup and Sync":
                settingsStackPane.getChildren().add(createBackupAndSyncSettings());
                break;
            case "Advanced Settings":
                settingsStackPane.getChildren().add(createAdvancedSettings());
                break;
            default:
                settingsStackPane.getChildren().add(new Label("No settings available."));
                break;
        }
    }

    private VBox createAppearanceAndBehaviourSettings() {
        return new VBox();
    }

    private VBox createKeybindSettings() {
        return new VBox();
    }

    private VBox createEditorSettings() {
        return new VBox();
    }

    private VBox createVersionControlSettings() {
        return new VBox();
    }

    private VBox createLanguagesSettings() {
        return new VBox();
    }

    private VBox createToolsSettings() {
        return new VBox();
    }

    private VBox createBackupAndSyncSettings() {
        return new VBox();
    }

    private VBox createAdvancedSettings() {
        return new VBox();
    }

    @FXML
    private void setupSettingsTabs() {
        ObservableList<String> settingsList = FXCollections.observableArrayList(
                "Appearance and Behaviour",
                "Keybinds",
                "Editor",
                "Version Control",
//                "Plugins",
//                "Build, Execution and Deployment",
                "Languages",
                "Tools",
                "Backup and Sync",
                "Advanced Settings");
        settingsGroupsList.setItems(settingsList);
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
