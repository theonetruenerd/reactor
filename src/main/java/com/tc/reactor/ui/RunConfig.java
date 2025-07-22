package com.tc.reactor.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class RunConfig {

    ObservableList<String> exeList =
            FXCollections.observableArrayList("C:\\Program Files (x86)\\HAMILTON\\Bin\\HxRun.exe");

    @FXML
    public ComboBox<String> exeComboBox;

    @FXML private void initialize() {
        exeComboBox.setItems(exeList);
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
}
