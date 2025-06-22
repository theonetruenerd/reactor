package com.tc.reactor;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Reactor");
        stage.setScene(scene);

        com.tc.reactor.ui.MainView mainView = loader.getController();
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
