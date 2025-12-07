package com.comp2042;

import com.comp2042.logic.bricks.Brick;

/**
 * Handles rotation logic for a Tetris brick.
 * <p>
 * This class tracks the current rotation of a brick and provides
 * methods to get the current shape, the next rotation, and update
 * the brick being rotated.
 */
public class BrickRotator {

    private Brick brick;
    private int currentShape = 0;

    /**
     * Returns information about the next rotation of the current brick.
     * 
     * @return a NextShapeInfo object containing the next shape matrix
     *         and the index of the rotation
     */
    public NextShapeInfo getNextShape() {
        int nextShape = currentShape;
        nextShape = (++nextShape) % brick.getShapeMatrix().size();
        return new NextShapeInfo(brick.getShapeMatrix().get(nextShape), nextShape);
    }

    /**
     * Returns the current shape of the brick.
     * 
     * @return a 2D integer array representing the current brick shape
     */
    public int[][] getCurrentShape() {
        return brick.getShapeMatrix().get(currentShape);
    }

    /**
     * Sets the current rotation index of the brick.
     * 
     * @param currentShape the rotation index to set (usually 0 to number of rotations - 1)
     */
    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }

    /**
     * Sets the brick to be rotated and resets the rotation to the initial position.
     * 
     * @param brick the Brick object to rotate
     */
    public void setBrick(Brick brick) {
        this.brick = brick;
        currentShape = 0;
    }
}
