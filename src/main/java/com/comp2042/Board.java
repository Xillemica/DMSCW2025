package com.comp2042;

/**
 * Represents a Tetris game board and defines the basic operations 
 * for moving, rotating, and managing bricks on the board.
 * 
 * <p>Implementing classes should handle brick creation, movement, rotation,
 * collision detection, row clearing, scoring, and board state.</p>
 */
public interface Board {

    /**
     * Moves the current brick down by one row.
     * 
     * @return true if the brick was successfully moved down,
     *         false if the movement is blocked (e.g., collision or bottom of the board)
     */
    boolean moveBrickDown();

    /**
     * Moves the current brick left by one column.
     * 
     * @return true if the brick was successfully moved left,
     *         false if the movement is blocked
     */
    boolean moveBrickLeft();

    /**
     * Moves the current brick right by one column.
     * 
     * @return true if the brick was successfully moved right,
     *         false if the movement is blocked
     */
    boolean moveBrickRight();

    /**
     * Rotates the current brick 90 degrees counter-clockwise.
     * 
     * @return true if the rotation is successful,
     *         false if the rotation is blocked (e.g., collision)
     */
    boolean rotateLeftBrick();

    /**
     * Creates a new brick at the spawn point on the board.
     * 
     * @return true if a collision occurs immediately (indicating game over),
     *         false otherwise
     */
    boolean createNewBrick();

    /**
     * Returns the current state of the board matrix.
     * 
     * @return a 2D integer array representing the board grid,
     *         where occupied cells are non-zero and empty cells are zero
     */
    int[][] getBoardMatrix();

    /**
     * Returns view data representing the current brick and the next brick.
     * 
     * @return a ViewData object containing the position and shape
     *         of the current brick and the next brick
     */
    ViewData getViewData();

    /**
     * Merges the current brick into the background matrix permanently.
     * This is called when the brick can no longer move down.
     */
    void mergeBrickToBackground();

    /**
     * Checks for and removes completed rows from the board.
     * 
     * @return a ClearRow object containing details about the removed rows
     *         including the number of lines cleared and the updated board matrix
     */
    ClearRow clearRows();

    /**
     * Returns the score object associated with this board.
     * 
     * @return the Score object that tracks the player's score
     */
    Score getScore();

    /**
     * Resets the board and prepares for a new game.
     * This includes clearing the board and spawning the first brick.
     */
    void newGame();
}

