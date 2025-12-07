package com.comp2042;

/**
 * Represents information about the next Tetris brick that will appear in the game.
 * <p>
 * Stores the shape matrix and its horizontal position.
 * The shape matrix is returned as a copy to prevent external modification.
 */
public final class NextShapeInfo {

    /** The shape matrix of the next brick. */
    private final int[][] shape;

    /** The horizontal position of the next brick. */
    private final int position;

    /**
     * Constructs a new {@code NextShapeInfo} with the specified shape and position.
     *
     * @param shape the shape matrix of the brick
     * @param position the horizontal position of the brick
     */
    public NextShapeInfo(final int[][] shape, final int position) {
        this.shape = shape;
        this.position = position;
    }

    /**
     * Returns a copy of the shape matrix of the next brick.
     *
     * @return a copy of the shape matrix
     */
    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    /**
     * Returns the horizontal position of the next brick.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }
}
