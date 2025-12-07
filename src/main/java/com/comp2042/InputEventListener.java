package com.comp2042;

/**
 * Defines methods for handling game input events.
 * <p>
 * Classes implementing this interface respond to player actions
 * such as moving, rotating, and dropping bricks, as well as
 * creating a new game, and update the game state accordingly.
 */
public interface InputEventListener {

    /**
     * Handles a "down" movement event for a brick.
     *
     * @param event the move event
     * @return a {@link DownData} object containing cleared row information and updated view
     */
    DownData onDownEvent(MoveEvent event);

    /**
     * Handles a "left" movement event for a brick.
     *
     * @param event the move event
     * @return the updated {@link ViewData} after moving left
     */
    ViewData onLeftEvent(MoveEvent event);

    /**
     * Handles a "right" movement event for a brick.
     *
     * @param event the move event
     * @return the updated {@link ViewData} after moving right
     */
    ViewData onRightEvent(MoveEvent event);

    /**
     * Handles a "rotate" movement event for a brick.
     *
     * @param event the move event
     * @return the updated {@link ViewData} after rotating the brick
     */
    ViewData onRotateEvent(MoveEvent event);
    
    /**
     * Handles a "hard drop" event for a brick.
     *
     * @return the updated {@link ViewData} after performing the hard drop
     */
    ViewData onHardDropEvent();

    /**
     * Starts a new game by resetting the board and game state.
     */
    void createNewGame();
}
