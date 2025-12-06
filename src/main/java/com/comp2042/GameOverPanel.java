package com.comp2042;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameOverPanel extends BorderPane {

    private final Label scoreLabel;

    public GameOverPanel() {
        this.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-alignment: center;");
        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setStyle("-fx-font-size: 48px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        gameOverLabel.getStyleClass().add("gameOverStyle");

        scoreLabel = new Label("Score: 0");
        scoreLabel.getStyleClass().add("gameOverScoreStyle");
        scoreLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #ffcc00; -fx-font-weight: bold;");

        VBox layout = new VBox(10, gameOverLabel, scoreLabel);
        layout.setStyle("-fx-alignment: center;");

        setCenter(layout);
    }

    public void bindScore(IntegerProperty scoreProperty) {
        scoreLabel.textProperty().bind(
                scoreProperty.asString("Score: %d")
        );
    }
}
