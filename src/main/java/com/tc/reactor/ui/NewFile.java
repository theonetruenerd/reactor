package com.tc.reactor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.HexFormat;

public class NewFile {
    @FXML private TextField libraryNameField;
    @FXML private TextField majorIdField;
    @FXML private TextField libraryVersionField;
    @FXML private TextArea parentNamespaceField;
    @FXML private TextArea libraryDescriptionField;
    @FXML private TextField libraryPathField;

    @FXML
    private void onBrowseButtonClick() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Library Path");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            libraryPathField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void onCreateButtonClick() {
        // Close the window when user clicks "Create"
        Stage stage = (Stage) libraryNameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Getters for user inputs.
     */
    public String getLibraryName() {
        return libraryNameField.getText().trim();
    }

    public String getMajorId() {
        try {
            return majorIdField.getText().trim().toUpperCase();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Major ID format: " + e.getMessage());
            return null;
        }
    }

    public Integer getLibraryVersion() {
        try {
            return Integer.parseInt(libraryVersionField.getText().trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid library version: " + e.getMessage());
            return null;
        }
    }

    public Path getLibraryPath() {
        String pathText = libraryPathField.getText().trim();
        return pathText.isBlank() ? null : Path.of(pathText);
    }

    public Optional<String> getParentNamespace() {
        String input = parentNamespaceField.getText().trim();
        return input.isBlank() ? Optional.empty() : Optional.of(input);
    }

    public Optional<String> getLibraryDescription() {
        String input = libraryDescriptionField.getText().trim();
        return input.isBlank() ? Optional.empty() : Optional.of(input);
    }
}