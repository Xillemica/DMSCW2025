package com.comp2042;

import java.awt.Point;

import com.comp2042.logic.bricks.Brick;
import com.comp2042.logic.bricks.BrickGenerator;
import com.comp2042.logic.bricks.RandomBrickGenerator;

public class SimpleBoard implements Board {

    private static final Point SPAWN_POINT = new Point(4, 10);

    private final int width;
    private final int height;
    private final BrickGenerator brickGenerator;
    private final BrickRotator brickRotator;
    private int[][] currentGameMatrix;
    private Point currentOffset;
    private final Score score;

    public SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        currentGameMatrix = new int[width][height];
        brickGenerator = new RandomBrickGenerator();
        brickRotator = new BrickRotator();
        score = new Score();
    }

    @Override
    public boolean moveBrickDown() {
        return moveBrick(0, 1);
    }
    
    @Override
    public boolean moveBrickLeft() {
        return moveBrick(-1, 0);
    }
    
    @Override
    public boolean moveBrickRight() {
        return moveBrick(1, 0);
    }
    
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
    
    @Override
    public boolean createNewBrick() {
        Brick newBrick = brickGenerator.getBrick();
        brickRotator.setBrick(newBrick);
        currentOffset = new Point(SPAWN_POINT);
        return MatrixOperations.intersect(currentGameMatrix, brickRotator.getCurrentShape(), currentOffset.x, currentOffset.y);
    }
    
    @Override
    public int[][] getBoardMatrix() {
        return currentGameMatrix;
    }
    
    @Override
    public ViewData getViewData() {
        return new ViewData(
            brickRotator.getCurrentShape(),
            currentOffset.x,
            currentOffset.y,
            brickGenerator.getNextBrick().getShapeMatrix().get(0)
    );
}

    @Override
    public void mergeBrickToBackground() {
        currentGameMatrix = MatrixOperations.merge(currentGameMatrix, brickRotator.getCurrentShape(), (int) currentOffset.getX(), (int) currentOffset.getY());
    }

    @Override
    public ClearRow clearRows() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        currentGameMatrix = clearRow.getNewMatrix();
        return clearRow;

    }

    @Override
    public Score getScore() {
        return score;
    }


    @Override
    public void newGame() {
        currentGameMatrix = new int[width][height];
        score.reset();
        createNewBrick();
    }
}
