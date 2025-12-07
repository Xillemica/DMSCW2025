package com.comp2042;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * Represents a temporary UI panel that displays score notifications or bonuses in the game.
 * <p>
 * Provides animations such as fading out and moving upwards before disappearing.
 */
public class NotificationPanel extends BorderPane {

    /** Duration of the fade-out animation in milliseconds. */
    private static final int FADE_DURATION_MS = 2000;

    /** Duration of the upward translate animation in milliseconds. */
    private static final int TRANSLATE_DURATION_MS = 2500;

    /** Vertical offset for the upward movement animation. */
    private static final int MOVE_OFFSET_Y = -40;

    /**
     * Constructs a new {@code NotificationPanel} with the specified text.
     *
     * @param text the text to display in the notification panel
     */
    public NotificationPanel(String text) {
        setMinHeight(200);
        setMinWidth(220);

        Label label = new Label(text);
        label.getStyleClass().add("bonusStyle");
        label.setEffect(new Glow(0.6));
        setCenter(label);
    }

    /**
     * Displays the notification with animations and removes it from the parent list
     * once the animation is finished.
     *
     * @param parentList the list of nodes containing this notification panel
     */
    public void showScore(ObservableList<Node> parentList) {
        FadeTransition fade = createFadeTransition();
        TranslateTransition translate = createTranslateTransition();

        ParallelTransition animation = new ParallelTransition(translate, fade);
        animation.setOnFinished(e -> parentList.remove(NotificationPanel.this));
        animation.play();
    }

    /**
     * Creates a fade-out transition for the notification panel.
     *
     * @return the {@link FadeTransition} object
     */
    private FadeTransition createFadeTransition() {
        FadeTransition fade = new FadeTransition(Duration.millis(FADE_DURATION_MS), this);
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }

    /**
     * Creates a translate transition that moves the notification panel upwards.
     *
     * @return the {@link TranslateTransition} object
     */
    private TranslateTransition createTranslateTransition() {
        TranslateTransition translate = new TranslateTransition(Duration.millis(TRANSLATE_DURATION_MS), this);
        translate.setToY(getLayoutY() + MOVE_OFFSET_Y);
        return translate;
    }
}