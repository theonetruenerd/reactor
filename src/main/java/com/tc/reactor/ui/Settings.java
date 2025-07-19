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
            if (newVal != null) {
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
            case "General":
                settingsStackPane.getChildren().add(createGeneralSettings());
                break;
            case "Editor":
                settingsStackPane.getChildren().add(createEditorSettings());
                break;
            case "Git":
                settingsStackPane.getChildren().add(createGitSettings());
                break;
            case "Keybinds":
                settingsStackPane.getChildren().add(createKeybindSettings());
                break;
            default:
                settingsStackPane.getChildren().add(new Label("No settings available."));
                break;
        }
    }

    private VBox createGeneralSettings() {
        VBox generalSettings = new VBox(10);
        generalSettings.getChildren().add(new Label("General settings"));
        return generalSettings;
    }

    private VBox createEditorSettings() {
        VBox editorSettings = new VBox(10);
        editorSettings.getChildren().addAll(
                new Label("Editor Settings"),
                new CheckBox("Enable Syntax Highlighting"),
                new Label("Font Size:"),
                new Spinner<>(8, 32, 12, 1),
                new Label("Font Family:"),
                new ComboBox<>(FXCollections.observableArrayList("Arial", "Courier New", "Consolas", "DejaVu Sans Mono")),
                new Label("Font Style:"),
                new ComboBox<>(FXCollections.observableArrayList("Regular", "Bold", "Italic", "Bold Italic")),
                new Label("Line Spacing:"),
                new Spinner<>(0.25, 1.5, 1, 0.25)
        );
        return editorSettings;
    }

    private VBox createGitSettings() {
        VBox gitSettings = new VBox(10);
        gitSettings.getChildren().add(new Label("Git settings"));
        return gitSettings;
    }

    private VBox createKeybindSettings() {
        VBox keybindSettings = new VBox(10);
        keybindSettings.getChildren().add(new Label("Keybind settings"));
        return keybindSettings;
    }

    @FXML
    private void setupSettingsTabs() {
        ObservableList<String> settingsList = FXCollections.observableArrayList(
                "General",
                "Editor",
                "Git",
                "Keybinds");
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
