package com.comp2042;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class MatrixOperations {

    private MatrixOperations() {
        //prevent initiation
    }

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

    private static boolean isOutOfBounds(int[][] matrix, int x, int y) {
        return x < 0 ||
               y < 0 ||
               y >= matrix.length ||
                   x >= matrix[y].length;
    }

    public static int[][] copy(int[][] original) {
        int[][] copy = new int[original.length][];

        for (int i = 0; i < original.length; i++) {
            copy[i] = new int[original[i].length];
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }

        return copy;
    }

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

    public static List<int[][]> deepCopyList(List<int[][]> list) {
        List<int[][]> result = new ArrayList<>(list.size());
        for (int[][] matrix : list) {
            result.add(copy(matrix));
        }
        return result;
    }
}