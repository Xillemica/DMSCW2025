package com.comp2042;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a player's score in the game.
 * <p>
 * Provides methods to retrieve, increment, and reset the score,
 * using a JavaFX {@link IntegerProperty} for easy binding with UI elements.
 */
public final class Score {

    /** The current score stored as a JavaFX IntegerProperty. */
    private final IntegerProperty score = new SimpleIntegerProperty(0);

    /**
     * Returns the {@link IntegerProperty} representing the score.
     * <p>
     * This allows the score to be bound to UI elements for automatic updates.
     *
     * @return the score property
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Adds the specified value to the current score.
     *
     * @param i the amount to add to the score
     */
    public void add(int i){
        score.setValue(score.getValue() + i);
    }

    /**
     * Resets the score to zero.
     */
    public void reset() {
        score.setValue(0);
    }
}
