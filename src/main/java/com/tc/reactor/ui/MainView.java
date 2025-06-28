package com.tc.reactor.ui;

import com.tc.reactor.support.editor.CodeAutocompletion;
import com.tc.reactor.support.editor.CodeFormatter;
import com.tc.reactor.support.editor.SyntaxManager;
import com.tc.reactor.support.git.GitUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.fxmisc.richtext.CodeArea;
import com.tc.reactor.support.languages.hsl.LibraryHandler;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import java.util.Optional;

public class MainView {

    // Defining the FXML classes:
    @FXML private MenuBar menuBar;
    @FXML private TreeView<String> projectTree;
    @FXML private TabPane mainTabPane;
    @FXML private TabPane bottomTabPane;
    @FXML private TextArea terminalTextArea;
    @FXML private TextArea outputTextArea;
    @FXML private TextArea logsTextArea;
    @FXML private TreeView<String> gitCommitTreeView;
    @FXML private TextArea commitMessageTextArea;

    private final GitUtils gitUtils = new GitUtils();
    private final Map<String, String> fileMap = new HashMap<>();


    /**
     * Initializes the window, setting up initial tabs
     */
    @FXML
    private void initialize() {
        setupInitialTabs();
        Platform.runLater(this::setupKeyboardShortcuts);
    }

    private void setupKeyboardShortcuts() {
        menuBar.getScene().addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN).match(event))
            {
                saveCurrentFile();
                event.consume();
            }
        });
    }

    /**
     * Handles the click event for the open project menu item. Opens a folder browser and
     * populates the project tree tab with files from the selected directory.
     */
    @FXML
    private void onOpenProjectClick() throws GitAPIException, IOException {
        // Opens folder browser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Project Directory");
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        // Checks if the user did select a directory
        if (selectedDirectory != null) {
            // Populates the project tree tab from the directory
            populateProjectTree(selectedDirectory);
            handleRepositoryInitialization(selectedDirectory);
        } else {
            System.out.println("No directory selected");
        }
        System.out.println(gitUtils.getRepository());
    }


    private void handleRepositoryInitialization(File selectedDirectory) {
        try {
            gitUtils.setRepository(selectedDirectory.getAbsolutePath());
            System.out.println("Git repository loaded: " + gitUtils.getRepository().getDirectory().getAbsolutePath());
        } catch (RepositoryNotFoundException e) {
            showGitRepositoryDialog(selectedDirectory);
        } catch (IOException e) {
            showErrorDialog("Error while accessing Git repository.", e.getMessage());
        }
    }

    private void showGitRepositoryDialog(File selectedDirectory) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Git Repository Not Found");
        alert.setHeaderText("This folder is not a Git repository.");
        alert.setContentText("Would you like to create a new Git repository in the selected folder?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                createGitRepository(selectedDirectory);
            }
        });
    }

    private void createGitRepository(File selectedDirectory) {
        try {
            gitUtils.createRepository(selectedDirectory.getAbsolutePath());
            System.out.println("Git repository created: " + gitUtils.getRepository().getDirectory().getAbsolutePath());
        } catch (Exception e) {
            showErrorDialog("Error while creating Git repository.", e.getMessage());
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
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
        addFilesToTreeItem(rootDirectory, rootItem, fileMap);

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
//        result.setValue(fileTree.getValue().getAbsolutePath()); // Use a full path in value
        return result;
    }

    /**
     * Recursively adds files to the specified tree item.
     *
     * @param directory the directory to start populating the tree
     * @param parentItem the parent tree item to add the files to
     * @param fileMap the hashmap being used to associate file names with file paths
     */
    private void addFilesToTreeItem(File directory, TreeItem<File> parentItem, Map<String, String> fileMap) {
        // Makes a list of files from the directory
        File[] files = directory.listFiles();
        // If there are no files, returns
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName(); // Extract the file name
                    parentItem.getChildren().add(new TreeItem<>(file));
                    fileMap.put(fileName, file.getAbsolutePath()); // Map file name to full path
                }
            }
        }

    }

    /**
     * Handles the click event to close a project.
     * This method performs two actions:
     * - Clears the project tree to remove all listed files and directories.
     * - Closes all open tabs in the main tab pane, effectively resetting the workspace.
     */
    public void onCloseProjectClick() {
        clearTree();
        closeAllTabs();
    }

    /**
     * Clears the content of the project tree by resetting its root node.
     * This effectively removes all displayed items from the tree view.
     */
    private void clearTree() {
    	projectTree.setRoot(new TreeItem<>(""));
    }

    private void saveCurrentFile() {
        Tab currentTab = mainTabPane.getSelectionModel().getSelectedItem();
        if (currentTab == null || currentTab.getUserData() == null) {
            System.out.println("No active file to save.");
            return; // Skip if no file is loaded
        }

        // Retrieve file path from the tab's userData
        String filePath = currentTab.getUserData().toString();
        if (filePath.isBlank()) {
            System.out.println("Invalid file path.");
            return; // Skip if file path is invalid
        }

        // Extract the content of the editor
        CodeArea editor = (CodeArea) currentTab.getContent();
        String fileContent = editor.getText();

        // Call save logic and print status
        saveFile(filePath, fileContent);
        System.out.println("File saved: " + filePath);
    }


    public void saveFile(String filePath, String fileContent) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();
            if (!dirsCreated) {
                System.err.println("Failed to create directories for: " + parentDir.getAbsolutePath());
                return;
            }
        }

        // Write content to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent);
            System.out.println("File saved successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save file: " + filePath);
            e.printStackTrace();

        }
    }

    @FXML
    public void onCreateHslLibraryClick() {
        try {
            // Load NewFile.fxml for the input form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tc/reactor/fxml/NewFile.fxml"));
            Parent root = loader.load();

            // Get the controller to retrieve user inputs
            NewFile controller = loader.getController();

            // Create and show the modal dialog
            Stage stage = new Stage();
            stage.setTitle("Create New HSL Library");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Block other UI interaction
            stage.showAndWait(); // Wait for the user to close the dialog

            // Retrieve user inputs from the controller
            String libraryName = controller.getLibraryName();
            HexFormat majorId = HexFormat.ofDelimiter(controller.getMajorId());
            Integer libraryVersion = controller.getLibraryVersion();
            Path libraryPath = controller.getLibraryPath();
            Optional<String> parentNamespace = controller.getParentNamespace();
            Optional<String> libraryDescription = controller.getLibraryDescription();

            // Validate inputs and create the library
            if (libraryName != null && majorId != null && libraryVersion != null && libraryPath != null) {
                LibraryHandler libraryHandler = new LibraryHandler();
                libraryHandler.CreateLibrary(
                        libraryName, majorId, libraryVersion, libraryPath, parentNamespace, libraryDescription
                );
                openFileInTab(libraryPath.resolve(libraryName + ".hsl").toString());
            } else {
                System.out.println("Library creation aborted: Missing required fields.");
            }
        } catch (IOException e) {
            System.err.println("Error loading NewFile.fxml: " + e.getMessage());
        }

    }

    @FXML
    public void onCommitButtonClick() {
        try {
            gitUtils.commit(commitMessageTextArea.getText());
            System.out.println("Commit successful.");
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onRefreshCommitButtonClick() throws GitAPIException {
        TreeItem<String> changes = gitUtils.getUncommittedChanges();
        Platform.runLater(() -> {
            gitCommitTreeView.setRoot(changes);
        });
    }

    /**
     * Closes all open tabs in the main tab pane.
     * This method removes all tabs currently displayed in the main tab pane
     * by clearing its tab list.
     */
    private void closeAllTabs() {
        mainTabPane.getTabs().clear();
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

            // Use fileMap to retrieve the full path using the displayed file name
            String fileName = selectedItem.getValue();
            String fullPath = fileMap.get(fileName);

            if (fullPath != null) {
                // Opens the file
                openFileInTab(fullPath);
            } else {
                System.err.println("File path not found for: " + fileName);
            }
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
        CodeArea editor = new CodeArea();
        tab.setContent(editor);


        String extension = getFileExtension(file.getName());

        SyntaxManager syntaxManager = new SyntaxManager();
        syntaxManager.setupSyntaxHighlighting(extension, editor);
        CodeFormatter codeFormatter = new CodeFormatter();
        codeFormatter.setupAutoFormatting(editor, extension);

        // Setup code autocompletion for supported languages
        if ("hsl".equals(extension)) {
            new CodeAutocompletion(editor, extension);
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            editor.appendText(stringBuilder.toString());
        } catch (IOException e) {
            editor.appendText("Error reading file: " + e.getMessage());
        }
        mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
    }

    /**
     * Utility method to extract the file extension from a file name.
     *
     * @param fileName The name of the file
     * @return The file extension (e.g., "java", "css") or an empty string if none
     */
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return ""; // No extension
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

}
