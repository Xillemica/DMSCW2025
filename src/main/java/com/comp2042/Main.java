package com.comp2042;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point of the Tetris JavaFX application.
 * <p>
 * Launches the JavaFX runtime and displays the start screen.
 * Extends {@link Application} to initialize the primary stage.
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by initializing the scoreboard and showing the start screen.
     *
     * @param primaryStage the primary stage provided by the JavaFX runtime
     * @throws Exception if an error occurs during initialization
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        StartScreen startScreen = new StartScreen(scoreboard);
        startScreen.show(primaryStage);
    }

    /**
     * The main method which launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}