package com.tc.reactor.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Dictionary;

public class RunConfig {

    ObservableList<String> exeList =
            FXCollections.observableArrayList("C:\\Program Files (x86)\\HAMILTON\\Bin\\HxRun.exe");

    Path configFilePath = Path.of("C:\\Users\\TarunChapman\\IdeaProjects\\reactor\\src\\main\\resources\\run_configs.json");

    @FXML
    public ComboBox<String> exeComboBox;

    @FXML
    private TextField runConfigName;

    @FXML
    private TreeView<String> runConfigTreeView;

    @FXML
    private TextField runConfigArgs;

    TreeItem<String> runConfigRoots = new TreeItem<>("Run Configurations");

    @FXML private void initialize() throws IOException {
        exeComboBox.setItems(exeList);
        runConfigTreeView.setRoot(runConfigRoots);
        runConfigTreeView.setShowRoot(false);
        runConfigTreeView.setOnMouseClicked(event -> {
            try {
                onRunConfigTreeViewClick();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        loadRunConfigsFromFile();
    }

    public void loadRunConfigsFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        RunConfigSave[] runConfigs = objectMapper.readValue(configFilePath.toFile(), RunConfigSave[].class);
        System.out.println("Loaded " + runConfigs.length + " run configs from file.");
        System.out.println("Run configs:");
        System.out.println(Arrays.toString(runConfigs));
        for (RunConfigSave runConfig : runConfigs) {
            System.out.println("Run config: " + runConfig.configName);
            System.out.println("Exe: " + runConfig.exeName);

            TreeItem<String> exeItem = runConfigRoots.getChildren().stream()
                    .filter(item -> item.getValue().equals(runConfig.exeName))
                    .findFirst()
                    .orElseGet(() -> {
                        TreeItem<String> newExeItem = new TreeItem<>(runConfig.exeName);
                        newExeItem.setExpanded(true);
                        runConfigRoots.getChildren().add(newExeItem);
                        return newExeItem;
                    });


            if (exeItem.getChildren().stream().noneMatch(item -> item.getValue().equals(runConfig.configName))) {
                TreeItem<String> runConfigItem = new TreeItem<>(runConfig.configName);
                exeItem.getChildren().add(runConfigItem);
            }

        }
        System.out.println("Loaded run configs from file.");
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

    public String getArgsForRunConfig(String runConfigName, String exeName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        RunConfigSave[] runConfigs = objectMapper.readValue(configFilePath.toFile(), RunConfigSave[].class);

        for (RunConfigSave runConfig : runConfigs) {
            if (runConfig.configName.equals(runConfigName) && runConfig.exeName.equals(exeName)) {
                return runConfig.args;
            }
        }

        return "";
    }


    @FXML
    private void onRunConfigTreeViewClick() throws IOException {
        System.out.println("Run config tree view clicked.");
        TreeItem<String> selectedItem = runConfigTreeView.getSelectionModel().getSelectedItem();

        String args = getArgsForRunConfig(selectedItem.getValue(), selectedItem.getParent().getValue());

        if (!selectedItem.getParent().getValue().equals("Run Configurations")) {
            runConfigName.setText(selectedItem.getValue());
            exeComboBox.setValue(selectedItem.getParent().getValue());
            runConfigArgs.setText(args);
        } else {
            runConfigName.setText("");
            exeComboBox.setValue(null);
            runConfigArgs.setText("");
        }
    }

    @FXML
    private void onApplyButtonClick() throws IOException {
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Duplicate Run Configuration");
            alert.setHeaderText("A run configuration with the same name already exists.");
            alert.setContentText("Please choose a different name.");
            alert.showAndWait();
        }

        String args = runConfigArgs.getText();

        saveRunConfigToFile(runConfig.getValue(), exeName.getValue(), args);

    }

    private static class RunConfigSave {
        @JsonProperty String configName;
        @JsonProperty String exeName;
        @JsonProperty String args;
    }

    private void saveRunConfigToFile(@JsonProperty String configName, @JsonProperty String exeName, @JsonProperty String args) throws IOException {

        System.out.println(configName);
        System.out.println(exeName);
        System.out.println(args);

        ObjectMapper objectMapper = new ObjectMapper();

        RunConfigSave save = new RunConfigSave();
        save.configName = configName;
        save.exeName = exeName;
        save.args = args;

        if (!configFilePath.toFile().exists()) {
            objectMapper.writeValue(configFilePath.toFile(), new RunConfigSave[]{save});
            System.out.println("Created new JSON file.");
            return;
        }

        RunConfigSave[] existingConfigs = new RunConfigSave[0];
        try {
            existingConfigs = objectMapper.readValue(configFilePath.toFile(), RunConfigSave[].class);
        } catch (Exception e) {
            System.err.println("Failed to read existing configs: " + e.getMessage());
        }
        RunConfigSave[] updatedConfigs = new RunConfigSave[existingConfigs.length + 1];
        System.arraycopy(existingConfigs, 0, updatedConfigs, 0, existingConfigs.length);
        updatedConfigs[existingConfigs.length] = save;
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(configFilePath.toFile(), updatedConfigs);
        } catch (IOException e) {
            System.err.println("Failed to save run config to file: " + e.getMessage());
            throw e;
        }


        System.out.println("Saving run configs to file...");
    }
}
