package com.tc.reactor.ui;

import com.tc.reactor.support.CodeAutocompletion;
import com.tc.reactor.support.CodeFormatter;
import com.tc.reactor.support.SyntaxManager;
import com.tc.reactor.support.git.GitUtils;
import com.tc.reactor.support.languages.hsl.syntaxchecker.HslLexerLexer;
import com.tc.reactor.support.languages.hsl.syntaxchecker.HslLexerParser;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import org.antlr.v4.runtime.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.fxmisc.richtext.CodeArea;

import java.io.*;
import java.util.*;

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

    private GitUtils gitUtils = new GitUtils();
    private Task<Void> gitStatusTask;
    private boolean isListenerRunning = false;

    @FXML
    private TreeView<String> gitCommitTreeView;
    @FXML
    private Button commitButton;
    @FXML
    private TextArea commitMessageTextArea;
    @FXML
    private Button pushButton;

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

    public void startGitStatusListener() {
        if (gitUtils.getRepository() == null) {
            throw new IllegalStateException("Git repository is not initialized.");
        }

        isListenerRunning = true;

        gitStatusTask = new Task<>() {
            @Override
            protected Void call() {
                while (isListenerRunning) {
                    try {
                        // Retrieve uncommitted changes
                        TreeItem<String> changes = gitUtils.getUncommittedChanges();

                        // Update the UI on the JavaFX Application Thread
                        Platform.runLater(() -> gitCommitTreeView.setRoot(changes));

                        // Poll every 2 seconds (adjust as required)
                        Thread.sleep(2000);
                    } catch (GitAPIException | InterruptedException e) {
                        e.printStackTrace();
                        stopGitStatusListener();  // Stop listener on error
                        Platform.runLater(() -> gitCommitTreeView.setRoot(new TreeItem<>("Error: " + e.getMessage())));
                    }
                }

                return null;
            }
        };

        Thread statusThread = new Thread(gitStatusTask);
        statusThread.setDaemon(true);
        statusThread.start();
    }

    public void stopGitStatusListener() {
        isListenerRunning = false;
        if (gitStatusTask != null) {
            gitStatusTask.cancel();
        }
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
            try {
                // Attempt to load the Git repository from the selected directory
                gitUtils.setRepository(selectedDirectory.getAbsolutePath());
                System.out.println("Git repository loaded: " + gitUtils.getRepository().getDirectory().getAbsolutePath());
            } catch (RepositoryNotFoundException ex) {
                // If no Git repository is found, show a confirmation dialog to the user
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Git Repository Not Found");
                alert.setHeaderText("This folder is not a Git repository.");
                alert.setContentText("Would you like to create a new Git repository in the selected folder?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // User chooses to create a Git repository
                    try {
                        gitUtils.createRepository(selectedDirectory.getAbsolutePath());
                        System.out.println("New Git repository created at: " + selectedDirectory.getAbsolutePath());
                    } catch (Exception e) {
                        // Handle errors during repository creation
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Repository Creation Failed");
                        errorAlert.setHeaderText("An error occurred while creating the repository.");
                        errorAlert.setContentText(e.getMessage());
                        errorAlert.showAndWait();
                        System.err.println("Git Repository Creation Failed: " + e.getMessage());
                    }
                } else {
                    // User cancels repository creation
                    System.out.println("Git repository setup canceled by the user.");
                }
            } catch (IOException e) {
                // Handle general I/O exceptions when accessing the repository
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Error while checking the Git repository.");
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
                System.err.println("Error while checking repository: " + e.getMessage());
            }
        } else {
            System.out.println("No directory selected");
        }

        startGitStatusListener();
        System.out.println(gitUtils.getRepository());
    }

    /**
     * Populates the project tree tab with files from the selected directory.
     *
     * @param rootDirectory the root directory to start populating the tree
     */
    private void populateProjectTree(File rootDirectory) {
        // Gets the root file as the root directory
        TreeItem<File> rootItem = new TreeItem<>(rootDirectory);

        Map<String, String> fileMap = new HashMap<>();
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
        result.setValue(fileTree.getValue().getAbsolutePath()); // Use a full path in value
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
        stopGitStatusListener();
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
    public void onCommitButtonClick() {
        try {
            gitUtils.commit(commitMessageTextArea.getText());
            System.out.println("Commit successful.");
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
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

//        if(extension.equals("hsl")) {
//            editor.textProperty().addListener((obs, oldText, newText) -> {
//                checkSyntax(newText);
//            });

//        }

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

    /**
     * Handles the click event for the save menu item. This method is called when the user clicks on the "Save" button in the menu.
     */
    @FXML
    protected void onSaveMenuItemClick() {
        // TODO Add ability to save files
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

    public void checkSyntax(String sourceCode) {
        CharStream input = CharStreams.fromString(sourceCode);
        HslLexerLexer lexer = new HslLexerLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HslLexerParser parser = new HslLexerParser(tokens);

        // Add custom error listener BEFORE parsing
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                String errorMessage = String.format("Syntax Error at line %d:%d - %s%n", line, charPositionInLine, msg);

                // Update the output TextArea in a thread-safe way
                Platform.runLater(() -> {
                    if (outputTextArea != null) {
                        outputTextArea.appendText(errorMessage);
                    } else {
                        System.err.println("OutputTextArea is not initialized.");
                    }
                });
            }
        });

        // Parse the code (execute the entry rule)
        parser.hslFile();
    }

}
