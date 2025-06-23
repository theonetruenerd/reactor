package com.tc.reactor;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.io.InputStream;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load custom font and register it globally
        try (InputStream fontStream = Objects.requireNonNull(getClass().getResourceAsStream("/com/tc/reactor/fonts/JetBrainsMono-Regular.ttf"))) {
            javafx.scene.text.Font.loadFont(fontStream, 14);
        }

        // Load FXML and set up the scene
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/styles.css")).toExternalForm());
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.setTitle("Reactor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}