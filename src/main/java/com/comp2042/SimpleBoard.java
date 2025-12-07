package com.comp2042;

import java.awt.Point;

import com.comp2042.logic.bricks.Brick;
import com.comp2042.logic.bricks.BrickGenerator;
import com.comp2042.logic.bricks.RandomBrickGenerator;

/**
 * Implements the {@link Board} interface and represents the game board for Tetris.
 * <p>
 * Manages the current state of the board, the active brick, 
 * brick movement, rotation, row clearing, and scoring.
 */
public class SimpleBoard implements Board {

    /** The default spawn point for new bricks. */
    private static final Point SPAWN_POINT = new Point(4, 10);

    /** The width of the board. */
    private final int width;

    /** The height of the board. */
    private final int height;

    /** Generates new bricks for the game. */
    private final BrickGenerator brickGenerator;

    /** Handles rotation logic for the current brick. */
    private final BrickRotator brickRotator;

    /** The current game matrix representing the board state. */
    private int[][] currentGameMatrix;

    /** The current offset (position) of the active brick. */
    private Point currentOffset;

    /** The score object tracking the player's score. */
    private final Score score;

    /**
     * Constructs a new {@code SimpleBoard} with the specified width and height.
     *
     * @param width the width of the board
     * @param height the height of the board
     */
    public SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        currentGameMatrix = new int[width][height];
        brickGenerator = new RandomBrickGenerator();
        brickRotator = new BrickRotator();
        score = new Score();
    }

    /**
     * Moves the current brick down by one unit.
     *
     * @return {@code true} if the move is successful, {@code false} if blocked
     */
    @Override
    public boolean moveBrickDown() {
        return moveBrick(0, 1);
    }

    /**
     * Moves the current brick to the left by one unit.
     *
     * @return {@code true} if the move is successful, {@code false} if blocked
     */
    @Override
    public boolean moveBrickLeft() {
        return moveBrick(-1, 0);
    }

    /**
     * Moves the current brick to the right by one unit.
     *
     * @return {@code true} if the move is successful, {@code false} if blocked
     */
    @Override
    public boolean moveBrickRight() {
        return moveBrick(1, 0);
    }

    /**
     * Moves the current brick by the specified offset.
     *
     * @param dx horizontal movement
     * @param dy vertical movement
     * @return {@code true} if the move is successful, {@code false} if blocked
     */
    private boolean moveBrick(int dx, int dy) {
        int[][] matrixCopy = MatrixOperations.copy(currentGameMatrix);
        Point newOffset = new Point(currentOffset);
        newOffset.translate(dx, dy);
        if (MatrixOperations.intersect(matrixCopy, brickRotator.getCurrentShape(), newOffset.x, newOffset.y)) {
            return false;
        } else {
            currentOffset = newOffset;
            return true;
        }
    }

    /**
     * Rotates the current brick to the left (counterclockwise).
     *
     * @return {@code true} if rotation is successful, {@code false} if blocked
     */
    @Override
    public boolean rotateLeftBrick() {
        int[][] matrixCopy = MatrixOperations.copy(currentGameMatrix);
        NextShapeInfo nextShape = brickRotator.getNextShape();
        if (MatrixOperations.intersect(matrixCopy, nextShape.getShape(), currentOffset.x, currentOffset.y)) {
            return false;
        } else {
            brickRotator.setCurrentShape(nextShape.getPosition());
            return true;
        }
    }

    /**
     * Creates a new brick and sets it as the current active brick.
     *
     * @return {@code true} if the new brick immediately intersects (game over),
     *         {@code false} otherwise
     */
    @Override
    public boolean createNewBrick() {
        Brick newBrick = brickGenerator.getBrick();
        brickRotator.setBrick(newBrick);
        currentOffset = new Point(SPAWN_POINT);
        return MatrixOperations.intersect(currentGameMatrix, brickRotator.getCurrentShape(), currentOffset.x, currentOffset.y);
    }

    /**
     * Returns the current board matrix.
     *
     * @return a 2D integer array representing the board
     */
    @Override
    public int[][] getBoardMatrix() {
        return currentGameMatrix;
    }

    /**
     * Returns the {@link ViewData} representing the current active brick
     * and its position on the board.
     *
     * @return the view data for rendering
     */
    @Override
    public ViewData getViewData() {
        return new ViewData(
            brickRotator.getCurrentShape(),
            currentOffset.x,
            currentOffset.y,
            brickGenerator.getNextBrick().getShapeMatrix().get(0)
        );
    }

    /**
     * Merges the current brick into the background matrix permanently.
     */
    @Override
    public void mergeBrickToBackground() {
        currentGameMatrix = MatrixOperations.merge(currentGameMatrix, brickRotator.getCurrentShape(), (int) currentOffset.getX(), (int) currentOffset.getY());
    }

    /**
     * Clears any completed rows and returns the result including score bonus.
     *
     * @return the {@link ClearRow} containing cleared rows, new matrix, and score bonus
     */
    @Override
    public ClearRow clearRows() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        currentGameMatrix = clearRow.getNewMatrix();
        return clearRow;

    }

    /**
     * Returns the score object for tracking and updating the player's score.
     *
     * @return the {@link Score} object
     */
    @Override
    public Score getScore() {
        return score;
    }

    /**
     * Resets the board to start a new game.
     */
    @Override
    public void newGame() {
        currentGameMatrix = new int[width][height];
        createNewBrick();
    }
}
