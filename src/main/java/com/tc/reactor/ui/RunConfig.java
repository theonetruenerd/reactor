package com.tc.reactor.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class RunConfig {

    ObservableList<String> exeList =
            FXCollections.observableArrayList("C:\\Program Files (x86)\\HAMILTON\\Bin\\HxRunCtrl.exe","test");

    @FXML
    public ComboBox<String> exeComboBox = new ComboBox<>(exeList);

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
}
