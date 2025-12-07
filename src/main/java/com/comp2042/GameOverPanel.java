package com.comp2042;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Represents the "Game Over" overlay panel in the GUI.
 * <p>
 * Displays a "GAME OVER" message along with the player's final score.
 * Extends {@link BorderPane} to easily position elements in the center of the screen.
 */
public class GameOverPanel extends BorderPane {

    /** Label displaying the player's final score. */
    private final Label scoreLabel;

    /**
     * Constructs a new {@code GameOverPanel} with a "GAME OVER" message
     * and a score label initialized to 0.
     * <p>
     * The panel has a semi-transparent dark background and centered layout.
     */
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

    /**
     * Binds the score label to the given {@link IntegerProperty} so that
     * the score updates automatically whenever the property changes.
     *
     * @param scoreProperty the integer property representing the player's score
     */
    public void bindScore(IntegerProperty scoreProperty) {
        scoreLabel.textProperty().bind(
                scoreProperty.asString("Score: %d")
        );
    }
}
