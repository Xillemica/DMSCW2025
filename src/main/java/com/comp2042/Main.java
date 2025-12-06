package com.comp2042;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        StartScreen startScreen = new StartScreen(scoreboard);
        startScreen.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}