package com.tc.reactor.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class RunConfig {

    ObservableList<String> exeList =
            FXCollections.observableArrayList("C:\\Program Files (x86)\\HAMILTON\\Bin\\HxRun.exe");

    @FXML
    public ComboBox<String> exeComboBox;

    @FXML
    private TextField runConfigName;

    @FXML
    private TreeView<String> runConfigTreeView;

    @FXML
    private TextField runConfigArgs;

    TreeItem<String> runConfigRoots = new TreeItem<>("Run Configurations");

    @FXML private void initialize() {
        exeComboBox.setItems(exeList);
        runConfigTreeView.setRoot(runConfigRoots);
        runConfigTreeView.setShowRoot(false);
        runConfigTreeView.setOnMouseClicked(event -> onRunConfigTreeViewClick());
    }

    @FXML
    private void onExeComboBoxClick() {
        System.out.println("Selected: " + exeComboBox.getValue());
    }

    @FXML
    private void onCancelButtonClick() {
        System.out.println("Cancel button clicked.");
        Stage stage = (Stage) exeComboBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onExeBrowseButtonClick() {
        System.out.println("Browse button clicked.");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select executable file");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Executable files (*.exe)", "*.exe");
        fileChooser.getExtensionFilters().add(filter);
        File initialFile = new File("C:\\Program Files (x86)\\HAMILTON\\Bin");
        fileChooser.setInitialDirectory(initialFile);
        File selectedExe = fileChooser.showOpenDialog(new Stage());
        if (selectedExe != null) {
            exeComboBox.setValue(selectedExe.getAbsolutePath());
        }
    }

    @FXML
    private void onRunConfigTreeViewClick() {
        System.out.println("Run config tree view clicked.");
        TreeItem<String> selectedItem = runConfigTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && !selectedItem.getParent().getValue().equals("Run Configurations")) {
            runConfigName.setText(selectedItem.getValue());
            exeComboBox.setValue(selectedItem.getParent().getValue());
        } else {
            runConfigName.setText("");
            exeComboBox.setValue(null);
        }
    }

    @FXML
    private void onApplyButtonClick() {
        System.out.println("Apply button clicked.");
        String exe = exeComboBox.getSelectionModel().getSelectedItem();
        if (runConfigName.getText().isBlank()) {
            System.err.println("Run config name cannot be blank.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Run Configuration");
            alert.setHeaderText("The run configuration name cannot be blank.");
            alert.setContentText("Please enter a name.");
            alert.showAndWait();
            return;
        }
        if (exe == null) {
            System.err.println("No executable selected.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Run Configuration");
            alert.setHeaderText("No executable selected.");
            alert.setContentText("Please select an executable.");
            alert.showAndWait();
            return;
        }
        TreeItem<String> exeName = runConfigTreeView.getRoot().getChildren().stream()
                .filter(item -> item.getValue().equals(exe))
                .findFirst()
                .orElseGet(() -> {
                    TreeItem<String> newExeName = new TreeItem<>(exe);
                    runConfigTreeView.getRoot().getChildren().add(newExeName);
                    return newExeName;
                });


        exeName.setExpanded(true);

        TreeItem<String> runConfig = new TreeItem<>(runConfigName.getText());

        if (exeName.getChildren().stream().noneMatch(item -> item.getValue().equals(runConfigName.getText()))) {
            System.out.println("Adding run config: " + runConfigName.getText());
            exeName.getChildren().add(runConfig);
        } else {
            System.out.println("Run config already exists: " + runConfigName.getText());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Duplicate Run Configuration");
            alert.setHeaderText("A run configuration with the same name already exists.");
            alert.setContentText("Please choose a different name.");
            alert.showAndWait();
        }

        String args = runConfigArgs.getText();

        saveRunConfigToFile(runConfig.getValue(), exeName.getValue(), args);

    }

    private void saveRunConfigToFile(String configName, String exeName, String args) {

        System.out.println(configName);
        System.out.println(exeName);
        System.out.println(args);

        System.out.println("Saving run configs to file...");
        System.out.println("WARNING: Not Yet Implemented!");
    }
}
