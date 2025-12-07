package com.comp2042;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the initial screen of the Tetris game.
 * <p>
 * Displays the game title, high scores, controls instructions, and a button to start the game.
 * When the "Start Game" button is clicked, it initializes the main game interface.
 */
public class StartScreen {

    /** The scoreboard used to display top scores on the start screen. */
    private final Scoreboard scoreboard;

    /**
     * Constructs a {@code StartScreen} with the specified {@link Scoreboard}.
     *
     * @param scoreboard the scoreboard containing high scores
     */
    public StartScreen(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    /**
     * Displays the start screen on the specified {@link Stage}.
     * <p>
     * The screen shows the title, high scores, controls instructions, and a start button.
     * Clicking the start button loads the main game layout and starts the game.
     *
     * @param stage the JavaFX stage on which to display the start screen
     */
    public void show(Stage stage) {
        VBox root = new VBox(7);
        root.setStyle("-fx-alignment: center; -fx-padding: 25; -fx-background-color: #1e1e1e;");

        Label title = new Label("TETRIS");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        Label scoreLabel = new Label("High Scores:");
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #dddddd;");

        Label controlsLabel = new Label("Controls:");
        controlsLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #cccccc;");
        
        Label pauseLabel = new Label("P — Pause");
        pauseLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #aaaaaa;");
        
        Label newGameLabel = new Label("N — New Game");
        newGameLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #aaaaaa;");

        ListView<String> scoreList = new ListView<>();
        scoreList.setPrefSize(200, 200);
        scoreList.setStyle( "-fx-control-inner-background: #2e2e2e;" + "-fx-text-fill: #ffffff;" + "-fx-font-size: 16px;" );

        for (HighScore hs : scoreboard.getScores()) {
            scoreList.getItems().add(hs.getName() + " - " + hs.getScore());
        }

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: #444444; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 8 16 8 16;");
        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-background-color: #666666; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 8 16 8 16;"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: #444444; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 8 16 8 16;"));

        startButton.setOnAction(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("gameLayout.fxml"));
                Parent gameRoot = fxmlLoader.load();
                GuiController guiController = fxmlLoader.getController();
                
                @SuppressWarnings("unused")
                GameController gameController = new GameController(guiController);
                
                Scene gameScene = new Scene(gameRoot, 300, 510);
                stage.setScene(gameScene);
                stage.setTitle("TetrisJFX");
                stage.show();
            } catch (java.io.IOException | java.lang.NullPointerException ex) {
                System.err.println("Error loading game: " + ex.getMessage());
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Could not load game");
                    alert.setContentText("Failed to load game interface. Please check if all files are present.");
                    alert.showAndWait();
                }
            }
        );

        root.getChildren().addAll(title, scoreLabel, scoreList, controlsLabel, pauseLabel, newGameLabel, startButton);
        
        Scene scene = new Scene(root, 300, 510);
        stage.setScene(scene);
        stage.setTitle("Tetris Start");
        stage.show();
    }
}