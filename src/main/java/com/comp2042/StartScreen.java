package com.comp2042;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScreen {

    private final Scoreboard scoreboard;

    public StartScreen(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void show(Stage stage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center; -fx-padding: 50; -fx-background-color: #1e1e1e;");

        Label title = new Label("TETRIS");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        Label scoreLabel = new Label("High Scores:");
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #dddddd;");

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

        root.getChildren().addAll(title, scoreLabel, scoreList, startButton);
        
        Scene scene = new Scene(root, 300, 510);
        stage.setScene(scene);
        stage.setTitle("Tetris Start");
        stage.show();
    }
}