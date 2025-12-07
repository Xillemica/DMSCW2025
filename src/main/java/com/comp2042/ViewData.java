package com.comp2042;

/**
 * Represents the current visual state of a Tetris brick and the next brick in the queue.
 * <p>
 * Contains the brick's matrix data, its position on the board,
 * and the next brick's matrix data.
 */
public final class ViewData {

    /** The 2D integer array representing the current brick's shape and color data. */
    private final int[][] brickData;

    /** The x-coordinate of the current brick's position on the board. */
    private final int xPosition;

    /** The y-coordinate of the current brick's position on the board. */
    private final int yPosition;

    /** The 2D integer array representing the next brick's shape and color data. */
    private final int[][] nextBrickData;

    /**
     * Constructs a {@code ViewData} object with the specified brick data, position,
     * and next brick data.
     *
     * @param brickData the matrix representing the current brick
     * @param xPosition the x-coordinate of the current brick
     * @param yPosition the y-coordinate of the current brick
     * @param nextBrickData the matrix representing the next brick
     */
    public ViewData(int[][] brickData, int xPosition, int yPosition, int[][] nextBrickData) {
        this.brickData = brickData;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.nextBrickData = nextBrickData;
    }

    /**
     * Returns a deep copy of the current brick's matrix data.
     *
     * @return the 2D integer array of the current brick
     */
    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    /**
     * Returns the x-coordinate of the current brick's position.
     *
     * @return the x-coordinate
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * Returns the y-coordinate of the current brick's position.
     *
     * @return the y-coordinate
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * Returns a deep copy of the next brick's matrix data.
     *
     * @return the 2D integer array of the next brick
     */
    public int[][] getNextBrickData() {
        return MatrixOperations.copy(nextBrickData);
    }
}
