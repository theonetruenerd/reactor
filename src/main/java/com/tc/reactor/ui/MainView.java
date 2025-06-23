package com.tc.reactor.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainView {

    // Defining the FXML classes:
    // Line breaks in class definitions are where the sections split in the fxml
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem newProjectMenuItem;
    @FXML
    private MenuItem openProjectMenuItem;
    @FXML
    private MenuItem closeProjectMenuItem;
    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private Menu editMenu;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private Menu gitMenu;
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
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem settingsMenuItem;

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
    private TextArea terminalTextArea;
    @FXML
    private TextArea outputTextArea;
    @FXML
    private TextArea logsTextArea;

    /**
     * Initializes the window, setting up initial tabs
     */
    @FXML
    private void initialize() {
        setupInitialTabs();
    }

    /**
     * Handles the click event for the open project menu item. Opens a folder browser and
     * populates the project tree tab with files from the selected directory.
     */
    @FXML
    private void onOpenProjectClick() {
        // Opens folder browser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Project Directory");
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        // Checks if the user did select a directory
        if (selectedDirectory != null) {
            // Populates the project tree tab from the directory
            populateProjectTree(selectedDirectory);
        }
    }

    /**
     * Populates the project tree tab with files from the selected directory.
     *
     * @param rootDirectory the root directory to start populating the tree
     */
    private void populateProjectTree(File rootDirectory) {
        // Gets the root file as the root directory
        TreeItem<File> rootItem = new TreeItem<>(rootDirectory);
        // Sets it to be expanded in 1 tier by default
        rootItem.setExpanded(true);
        // Adds the files to the tree tab
        addFilesToTreeItem(rootDirectory, rootItem);

        TreeView<String> view = new TreeView<>();
        projectTree.setRoot(new TreeItem<>(rootDirectory.getName())); // Set placeholder
        projectTree.setRoot(wrapTreeWithFileNames(rootItem)); // Convert back to TreeView<String>
        projectTree.setOnMouseClicked(this::handleTreeClick); // Opens the file when the file is clicked
    }

    /**
     * Wraps the given file tree with file names.
     *
     * @param fileTree the root of the file tree to wrap
     * @return a new TreeItem representing the wrapped file tree
     */
    private TreeItem<String> wrapTreeWithFileNames(TreeItem<File> fileTree) {
        TreeItem<String> result = new TreeItem<>(fileTree.getValue().getName());
        for (TreeItem<File> child : fileTree.getChildren()) {
            result.getChildren().add(wrapTreeWithFileNames(child));
        }
        // Attach File as user data (optional but useful)
        result.setValue(fileTree.getValue().getAbsolutePath()); // Use a full path in value
        return result;
    }

    /**
     * Recursively adds files to the specified tree item.
     *
     * @param directory the directory to start populating the tree
     * @param parentItem the parent tree item to add the files to
     */
    private void addFilesToTreeItem(File directory, TreeItem<File> parentItem) {
        // Makes a list of files from the directory
        File[] files = directory.listFiles();
        // If there are no files, returns
        if (files == null) return;
        // Loops through a list of files
        for (File file : files) {
            // Gets as a child item
            TreeItem<File> childItem = new TreeItem<>(file);
            // Checks if a file is a directory
            if (file.isDirectory()) {
                // Expands directory and recurs
                childItem.setExpanded(true);
                addFilesToTreeItem(file, childItem);
            }
            // Adds a file to the tree if not a directory
            parentItem.getChildren().add(childItem);
        }
    }

    /**
     * Handles the click event on a tree item.
     *
     * @param event the mouse event that triggered this method call
     */
    private void handleTreeClick(MouseEvent event) {
        // Gets the selected item
        TreeItem<String> selectedItem = projectTree.getSelectionModel().getSelectedItem();
        // Checks if the selected item exists and whether it is a file
        if (selectedItem != null && selectedItem.isLeaf()) {
            // Gets the full file path
            String fullPath = selectedItem.getValue();
            // Opens the file
            openFileInTab(fullPath);
        }
    }

    /**
     * Opens a file in the current tab. If a tab for the same file already exists, it is selected instead.
     *
     * @param filePath the path to the file to be opened
     */
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
        tab.setUserData(filePath); // Store a file path for future reference
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

    /**
     * Initializes the window by setting up initial tabs.
     */
    private void setupInitialTabs() {
    }

    /**
     * Handles the click event for the close menu item.
     * Exits the application when clicked.
     */
    @FXML
    protected void onCloseMenuItemClick() {
        Platform.exit();
    }

    /**
     * Handles the click event for the save menu item. This method is called when the user clicks on the "Save" button in the menu.
     */
    @FXML
    protected void onSaveMenuItemClick() {
    }

    /**
     * Handles opening IDE settings - LOCATION NOT SET ERROR
     */
    @FXML
    protected void onSettingsMenuItemClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Settings.fxml"));
            Parent settingsRoot = fxmlLoader.load();

            Stage settingsStage = new Stage();
            settingsStage.setTitle("Settings");
            settingsStage.setScene(new Scene(settingsRoot));
            settingsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
