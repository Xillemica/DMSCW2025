package com.comp2042;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int SCENE_WIDTH = 300;
    private static final int SCENE_HEIGHT = 510;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupStage(primaryStage);
    }

    private void setupStage(Stage primaryStage) throws Exception {
        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();
        GuiController guiController = fxmlLoader.getController();

        primaryStage.setTitle("TetrisJFX");
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        new GameController(guiController);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
