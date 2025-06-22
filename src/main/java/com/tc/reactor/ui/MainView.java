package com.tc.reactor.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainView {

    @FXML
    private MenuItem newProjectMenuItem;
    @FXML
    private MenuItem openProjectMenuItem;
    @FXML
    private MenuItem closeProjectMenuItem;
    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem pullMenuItem;
    @FXML
    private MenuItem commitMenuItem;
    @FXML
    private MenuItem pushMenuItem;
    @FXML
    private MenuItem fetchMenuItem;
    @FXML
    private MenuItem checkoutMenuItem;
    @FXML
    private MenuItem mergeMenuItem;
    @FXML
    private MenuItem rebaseMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private TreeView<String> projectTree;

    @FXML
    private TabPane mainTabPane;

    @FXML
    private TabPane bottomTabPane;
    @FXML
    private Tab terminalTab;
    @FXML
    private Tab outputTab;
    @FXML
    private Tab logsTab;

    @FXML
    private void initialize() {
        setupInitialTabs();
    }

    @FXML
    private void onOpenProjectClick() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Project Directory");
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory != null) {
            populateProjectTree(selectedDirectory);
        }
    }

    private void populateProjectTree(File rootDirectory) {
        TreeItem<File> rootItem = new TreeItem<>(rootDirectory);
        rootItem.setExpanded(true);
        addFilesToTreeItem(rootDirectory, rootItem);

        TreeView<String> view = new TreeView<>();
        projectTree.setRoot(new TreeItem<>(rootDirectory.getName())); // Set placeholder
        projectTree.setRoot(wrapTreeWithFileNames(rootItem)); // Convert back to TreeView<String>
        projectTree.setOnMouseClicked(this::handleTreeClick);
    }

    private TreeItem<String> wrapTreeWithFileNames(TreeItem<File> fileTree) {
        TreeItem<String> result = new TreeItem<>(fileTree.getValue().getName());
        for (TreeItem<File> child : fileTree.getChildren()) {
            result.getChildren().add(wrapTreeWithFileNames(child));
        }
        // Attach File as user data (optional but useful)
        result.setValue(fileTree.getValue().getAbsolutePath()); // Use full path in value
        return result;
    }

    private void addFilesToTreeItem(File directory, TreeItem<File> parentItem) {
        File[] files = directory.listFiles();
        if (files == null) return;
        for (File file : files) {
            TreeItem<File> childItem = new TreeItem<>(file);
            if (file.isDirectory()) {
                childItem.setExpanded(true);
                addFilesToTreeItem(file, childItem);
            }
            parentItem.getChildren().add(childItem);
        }
    }

    private void handleTreeClick(MouseEvent event) {
        TreeItem<String> selectedItem = projectTree.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.isLeaf()) {
            String fullPath = selectedItem.getValue();
            openFileInTab(fullPath);
        }
    }

    private void openFileInTab(String filePath) {
        // Check if a tab for the file already exists
        for (Tab tab : mainTabPane.getTabs()) {
            if (tab.getUserData() != null && tab.getUserData().equals(filePath)) {
                // Switch to the existing tab
                mainTabPane.getSelectionModel().select(tab);
                return;
            }
        }

        // If no tab exists for the file, create a new one
        File file = new File(filePath);
        Tab tab = new Tab(file.getName());
        tab.setUserData(filePath); // Store file path for future reference
        TextArea editor = new TextArea();
        tab.setContent(editor);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            editor.setText(stringBuilder.toString());
        } catch (IOException e) {
            editor.setText("Error reading file: " + e.getMessage());
        }

        mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
    }

    private void setupInitialTabs() {
    }

    @FXML
    protected void onCloseMenuItemClick() {
        Platform.exit();
    }

    @FXML
    protected void onSaveMenuItemClick() {
    }

}
