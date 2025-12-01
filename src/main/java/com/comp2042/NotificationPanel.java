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

public class NotificationPanel extends BorderPane {

    private static final int FADE_DURATION_MS = 2000;
    private static final int TRANSLATE_DURATION_MS = 2500;
    private static final int MOVE_OFFSET_Y = -40;

    public NotificationPanel(String text) {
        setMinHeight(200);
        setMinWidth(220);

        Label label = new Label(text);
        label.getStyleClass().add("bonusStyle");
        label.setEffect(new Glow(0.6));
        setCenter(label);
    }

    public void showScore(ObservableList<Node> parentList) {
        FadeTransition fade = createFadeTransition();
        TranslateTransition translate = createTranslateTransition();

        ParallelTransition animation = new ParallelTransition(translate, fade);
        animation.setOnFinished(e -> parentList.remove(NotificationPanel.this));
        animation.play();
    }

    private FadeTransition createFadeTransition() {
        FadeTransition fade = new FadeTransition(Duration.millis(FADE_DURATION_MS), this);
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }

    private TranslateTransition createTranslateTransition() {
        TranslateTransition translate = new TranslateTransition(Duration.millis(TRANSLATE_DURATION_MS), this);
        translate.setToY(getLayoutY() + MOVE_OFFSET_Y);
        return translate;
    }
}