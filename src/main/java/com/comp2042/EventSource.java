package com.comp2042;

/**
 * Represents the origin of a game event in Tetris.
 * <p>
 * An event can be triggered either by the user (keyboard input)
 * or automatically by the game thread (e.g., automatic brick descent).
 */
public enum EventSource {
    /** Event triggered by user input. */
    USER,

    /** Event triggered by the game thread automatically. */
    THREAD
}
