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
        root.setStyle("-fx-alignment: center; -fx-padding: 50;");

        Label title = new Label("TETRIS");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        Label scoreLabel = new Label("High Scores:");
        scoreLabel.setStyle("-fx-font-size: 18px;");

        ListView<String> scoreList = new ListView<>();
        scoreList.setPrefSize(200, 200);

        for (HighScore hs : scoreboard.getScores()) {
            scoreList.getItems().add(hs.getName() + " - " + hs.getScore());
        }

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("gameLayout.fxml"));
                Parent gameRoot = fxmlLoader.load();
                GuiController guiController = fxmlLoader.getController();

                new GameController(guiController);

                Scene gameScene = new Scene(gameRoot, 300, 510);
                stage.setScene(gameScene);
                stage.setTitle("TetrisJFX");
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(title, scoreLabel, scoreList, startButton);

        Scene scene = new Scene(root, 300, 510);
        stage.setScene(scene);
        stage.setTitle("Tetris Start");
        stage.show();
    }
}
