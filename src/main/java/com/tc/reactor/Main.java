package com.tc.reactor;

import com.tc.reactor.ui.MainView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

public class Main extends Application {

    private static final String FONT_PATH = "/com/tc/reactor/fonts/JetBrainsMono-Regular.ttf";
    private static final String STYLESHEET_PATH = "css/styles.css";
    private static final String FXML_PATH = "fxml/MainView.fxml";

    @FXML private TextArea logsTextArea;

    @Override
    public void start(Stage stage) {
        registerFont(FONT_PATH);

        Scene scene = buildScene(FXML_PATH, STYLESHEET_PATH);

        if (scene != null) {
            setupStage(stage, scene);

            stage.show();
        }
    }

    private void registerFont(String fontPath) {
        try (InputStream fontStream = getClass().getResourceAsStream(fontPath)) {
            if (fontStream != null) {
                Font.loadFont(Objects.requireNonNull(fontStream), 14);
            } else {
                throw new IllegalArgumentException("Font not found: " + fontPath);
            }
        } catch (Exception e) {
            logsTextArea.appendText("Error loading font: " + fontPath + "\n");
            logsTextArea.appendText(e.getMessage() + "\n");
        }
    }

    private Scene buildScene(String fxmlPath, String stylesheetPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            Optional.ofNullable(getClass().getResource(stylesheetPath))
                    .ifPresent(css -> scene.getStylesheets().add(css.toExternalForm()));
            return scene;
        } catch (Exception e) {
            logsTextArea.appendText("Error loading FXML: " + fxmlPath + "\n");
            logsTextArea.appendText(e.getMessage() + "\n");
            return null;
        }
    }

    private void setupStage(Stage stage, Scene scene) {
        stage.setTitle("Reactor");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
    }

    public static void main(String[] args) {
        launch(args);
    }
}