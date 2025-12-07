package com.comp2042;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Provides static utility methods for manipulating 2D integer matrices.
 * <p>
 * Specifically designed for Tetris game operations such as collision detection,
 * merging bricks, copying matrices, and clearing filled rows.
 * <p>
 * This class cannot be instantiated.
 */
public final class MatrixOperations {

    /**
     * Private constructor to prevent instantiation.
     */
    private MatrixOperations() {
        // prevent instantiation
    }

    /**
     * Checks whether a brick intersects with a given matrix at the specified position.
     *
     * @param matrix the target game board matrix
     * @param brick the brick matrix
     * @param x the x-coordinate on the board where the brick's top-left corner is placed
     * @param y the y-coordinate on the board where the brick's top-left corner is placed
     * @return {@code true} if the brick intersects with existing filled cells or is out of bounds;
     *         {@code false} otherwise
     */
    public static boolean intersect(final int[][] matrix, final int[][] brick, int x, int y) {
        for (int row = 0; row < brick.length; row++) {
            for (int col = 0; col < brick[row].length; col++) {
                if (brick[row][col] == 0) {
                    continue;
                }
                int targetX = x + col;
                int targetY = y + row;
                if (isOutOfBounds(matrix, targetX, targetY) ||
                    matrix[targetY][targetX] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a position (x, y) is out of the bounds of the given matrix.
     *
     * @param matrix the matrix to check against
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return {@code true} if the coordinates are outside the matrix bounds;
     *         {@code false} otherwise
     */
    private static boolean isOutOfBounds(int[][] matrix, int x, int y) {
        return x < 0 ||
               y < 0 ||
               y >= matrix.length ||
               x >= matrix[y].length;
    }

    /**
     * Creates a deep copy of the given 2D matrix.
     *
     * @param original the matrix to copy
     * @return a new 2D matrix with the same contents as the original
     */
    public static int[][] copy(int[][] original) {
        int[][] copy = new int[original.length][];

        for (int i = 0; i < original.length; i++) {
            copy[i] = new int[original[i].length];
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }

        return copy;
    }

    /**
     * Merges a brick into a matrix at the specified position, returning a new matrix.
     *
     * @param matrix the original game board matrix
     * @param brick the brick to merge
     * @param x the x-coordinate where the brick's top-left corner will be placed
     * @param y the y-coordinate where the brick's top-left corner will be placed
     * @return a new matrix with the brick merged into it
     */
    public static int[][] merge(int[][] matrix, int[][] brick, int x, int y) {
        int[][] merged = copy(matrix);

        for (int row = 0; row < brick.length; row++) {
            for (int col = 0; col < brick[row].length; col++) {
                if (brick[row][col] != 0) {
                    merged[y + row][x + col] = brick[row][col];
                }
            }
        }

        return merged;
    }

    /**
     * Checks the matrix for fully filled rows and removes them.
     * <p>
     * Computes the number of rows cleared and the score bonus.
     *
     * @param matrix the matrix to check for full rows
     * @return a {@link ClearRow} object containing the number of lines removed, 
     *         the new matrix after removal, and the score bonus
     */
    public static ClearRow checkRemoving(final int[][] matrix) {
        int width = matrix[0].length;
        int height = matrix.length;

        int[][] result = new int[height][width];
        Deque<int[]> remainingRows = new ArrayDeque<>();
        List<Integer> clearedRows = new ArrayList<>();

        for (int row = 0; row < height; row++) {
            boolean full = true;

            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                clearedRows.add(row);
            } else {
                remainingRows.add(matrix[row].clone());
            }
        }

        int writeIndex = height - 1;
        while (!remainingRows.isEmpty()) {
            result[writeIndex--] = remainingRows.pollLast();
        }

        int rowsCleared = clearedRows.size();
        int scoreBonus = 50 * rowsCleared * rowsCleared;

        return new ClearRow(rowsCleared, result, scoreBonus);
    }

    /**
     * Creates a deep copy of a list of 2D matrices.
     *
     * @param list the list of matrices to copy
     * @return a new list containing deep copies of each matrix in the original list
     */
    public static List<int[][]> deepCopyList(List<int[][]> list) {
        List<int[][]> result = new ArrayList<>(list.size());
        for (int[][] matrix : list) {
            result.add(copy(matrix));
        }
        return result;
    }
}