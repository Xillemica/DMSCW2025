package com.comp2042;

/**
 * Encapsulates the result of a "move down" action in Tetris.
 * <p>
 * Contains both the updated board state and information about any cleared rows
 * resulting from the downward movement of the current brick.
 */
public final class DownData {

    private final ClearRow clearRow;
    private final ViewData viewData;

    /**
     * Constructs a DownData object with the specified cleared row information
     * and updated view data.
     * 
     * @param clearRow the result of any row clearing after moving the brick down
     * @param viewData the current visual state of the board and active brick
     */
    public DownData(ClearRow clearRow, ViewData viewData) {
        this.clearRow = clearRow;
        this.viewData = viewData;
    }

    /**
     * Returns the cleared row information from the downward move.
     * 
     * @return a ClearRow object containing lines removed and score bonus
     */
    public ClearRow getClearRow() {
        return clearRow;
    }

    /**
     * Returns the updated view data for the board and current brick.
     * 
     * @return a ViewData object representing the current state of the game
     */
    public ViewData getViewData() {
        return viewData;
    }
}
