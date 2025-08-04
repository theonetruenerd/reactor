package com.tc.reactor.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

    private GridPane createAppearanceAndBehaviourSettings() {
        // Making GridPane for tab
        GridPane appearanceAndBehaviourSettings = new GridPane();

        // Creating all elements
        Label appearanceAndBehaviourLabel = new Label("Appearance and Behaviour");
        Label appearanceLabel = new Label("Appearance");
        Label themeLabel = new Label("Theme");
        ComboBox<String> themeComboBox = new ComboBox<>();
        CheckBox syncWithOSCheckBox = new CheckBox("Sync with OS");
        Label colourSchemeLabel = new Label("Colour Scheme");
        ComboBox<String> editorColourSchemeComboBox = new ComboBox<>();
        Label systemSettingsLabel = new Label("System Settings");
        CheckBox confirmExitCheckBox = new CheckBox("Confirm before closing");
        CheckBox reopenOnStartupCheckBox = new CheckBox("Reopen projects on startup");
        Label openProjectInLabel = new Label("Open project in");
        RadioButton openProjectInCurrentWindowRadioButton = new RadioButton("Current Window");
        RadioButton openProjectInNewWindowRadioButton = new RadioButton("New Window");
        RadioButton openProjectAskRadioButton = new RadioButton("Ask");
        ToggleGroup openProjectInToggleGroup = new ToggleGroup();
        Label defaultProjectDirectoryLabel = new Label("Default project directory");
        TextField defaultProjectDirectoryTextField = new TextField();

        //  Populating theme combo box
        themeComboBox.getItems().addAll("Light", "Dark");
        themeComboBox.getSelectionModel().selectFirst();

        // Radio Button Group
        openProjectInCurrentWindowRadioButton.setToggleGroup(openProjectInToggleGroup);
        openProjectInNewWindowRadioButton.setToggleGroup(openProjectInToggleGroup);
        openProjectAskRadioButton.setToggleGroup(openProjectInToggleGroup);

        // Populating colour scheme combo box
        editorColourSchemeComboBox.getItems().addAll("Default", "High Contrast");
        editorColourSchemeComboBox.getSelectionModel().selectFirst();

        appearanceAndBehaviourLabel.setFont(Font.font(20));
        appearanceLabel.setFont(Font.font(16));
        systemSettingsLabel.setFont(Font.font(16));

        appearanceAndBehaviourSettings.setHgap(10);
        appearanceAndBehaviourSettings.setVgap(10);

        // Setting Constraints
        GridPane.setConstraints(appearanceAndBehaviourLabel, 0, 0, 3, 1);
        GridPane.setConstraints(appearanceLabel, 0, 1);
        GridPane.setConstraints(themeLabel, 0, 2);
        GridPane.setConstraints(themeComboBox, 1, 2);
        GridPane.setConstraints(syncWithOSCheckBox, 0, 3);
        GridPane.setConstraints(colourSchemeLabel, 0, 4);
        GridPane.setConstraints(editorColourSchemeComboBox, 1, 4);
        GridPane.setConstraints(systemSettingsLabel, 0, 5);
        GridPane.setConstraints(confirmExitCheckBox, 0, 6);
        GridPane.setConstraints(reopenOnStartupCheckBox, 0, 7);
        GridPane.setConstraints(openProjectInLabel, 0, 8);
        GridPane.setConstraints(openProjectInCurrentWindowRadioButton, 1, 8);
        GridPane.setConstraints(openProjectInNewWindowRadioButton, 2, 8);
        GridPane.setConstraints(openProjectAskRadioButton, 3, 8);
        GridPane.setConstraints(defaultProjectDirectoryLabel, 0, 9);
        GridPane.setConstraints(defaultProjectDirectoryTextField, 1, 9, 4,1);

        // Filling pane
        appearanceAndBehaviourSettings.getChildren().addAll(
                appearanceAndBehaviourLabel,
                appearanceLabel,
                themeLabel,
                themeComboBox,
                syncWithOSCheckBox,
                colourSchemeLabel,
                editorColourSchemeComboBox,
                systemSettingsLabel,
                confirmExitCheckBox,
                reopenOnStartupCheckBox,
                openProjectInLabel,
                openProjectInCurrentWindowRadioButton,
                openProjectInNewWindowRadioButton,
                openProjectAskRadioButton,
                defaultProjectDirectoryLabel,
                defaultProjectDirectoryTextField
        );
        return appearanceAndBehaviourSettings;
    }

    private VBox createKeybindSettings() {
        VBox keybindSettings = new VBox(10);
        keybindSettings.getChildren().addAll(
                new Label("Keybinds"),
                new Separator()
        );
        return keybindSettings;
    }

    private VBox createEditorSettings() {
        VBox editorSettings = new VBox(10);
        editorSettings.getChildren().addAll(
                new Label("Editor"),
                new Separator(),
                new Label("General"),
                new Separator(),
                new Label("Code Editing"),
                new Separator(),
                new Label("Font"),
                new Separator(),
                new Label("Colour Scheme"),
                new Separator(),
                new Label("Code Style"),
                new Separator(),
                new Label("TODO"),
                new Separator()
        );
        return editorSettings;
    }

    private VBox createVersionControlSettings() {
        VBox versionControlSettings = new VBox(10);
        versionControlSettings.getChildren().addAll(
                new Label("Version Control"),
                new Separator(),
                new Label("Changelists"),
                new Separator(),
                new Label("Commit"),
                new Separator(),
                new Label("Confirmation"),
                new Separator(),
                new Label("Log"),
                new Separator(),
                new Label("Git"),
                new Separator()
        );
        return versionControlSettings;
    }

    private VBox createLanguagesSettings() {
        VBox languagesSettings = new VBox(10);

        ToggleGroup group = new ToggleGroup();
        RadioButton htmlRadioButton = new RadioButton("WYSIWYG Editor");
        RadioButton textRadioButton = new RadioButton("Text Editor");

        htmlRadioButton.setToggleGroup(group);
        textRadioButton.setToggleGroup(group);
        htmlRadioButton.setSelected(true);

        languagesSettings.getChildren().addAll(
                new Label("Language Settings"),
                new Separator(),
                new Label("Venus"),
                new Separator(),
                new Label("HSL"),
                new Separator(),
                new Label("XAML"),
                new Separator(),
                new Label("CHM"),
                new Separator(),
                new Label("HTM"),
                new Text( "Which editor to use for HTM files: " ),
                htmlRadioButton,
                textRadioButton,
                new Separator()
        );
        return languagesSettings;
    }

    private VBox createToolsSettings() {
        VBox toolsSettings = new VBox(10);
        toolsSettings.getChildren().addAll(
                new Label("Tools"),
                new Separator(),
                new Label("CSV Formats"),
                new Separator(),
                new Label("Startup Tasks"),
                new Separator(),
                new Label("Terminal"),
                new Separator()
        );
        return toolsSettings;
    }

    private VBox createBackupAndSyncSettings() {
        VBox backupAndSyncSettings = new VBox(10);
        backupAndSyncSettings.getChildren().addAll(
                new Label("Backup and Sync"),
                new Separator()
        );
        return backupAndSyncSettings;
    }

    private VBox createAdvancedSettings() {
        VBox advancedSettings = new VBox(10);
        advancedSettings.getChildren().addAll(
                new Label("Advanced Settings"),
                new Separator()
        );
        return advancedSettings;
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
        System.out.println("Applying settings...");
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
