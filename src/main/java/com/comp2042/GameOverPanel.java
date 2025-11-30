package com.comp2042;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameOverPanel extends BorderPane {

    private final Label scoreLabel;

    public GameOverPanel() {
        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.getStyleClass().add("gameOverStyle");

        scoreLabel = new Label("Score: 0");
        scoreLabel.getStyleClass().add("gameOverScoreStyle");

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
