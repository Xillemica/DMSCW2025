package com.comp2042;

/**
 * Represents the type of movement event in the Tetris game.
 * <p>
 * Each event type corresponds to a possible action that can
 * be performed on the current brick.
 */
public enum EventType {
    /** Move the brick downwards by one row. */
    DOWN,

    /** Move the brick left by one column. */
    LEFT,

    /** Move the brick right by one column. */
    RIGHT,

    /** Rotate the brick 90 degrees clockwise. */
    ROTATE
}
