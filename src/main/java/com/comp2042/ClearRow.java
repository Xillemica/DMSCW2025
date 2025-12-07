package com.comp2042;

/**
 * Represents the result of clearing rows on the Tetris board.
 * <p>
 * Contains information about how many lines were removed, the resulting board matrix,
 * and the score bonus awarded for the cleared lines.
 */
public final class ClearRow {

    private final int linesRemoved;
    private final int[][] newMatrix;
    private final int scoreBonus;

    /**
     * Constructs a ClearRow object with the specified information.
     * 
     * @param linesRemoved the number of lines removed
     * @param newMatrix the new board matrix after clearing lines
     * @param scoreBonus the score bonus awarded for clearing lines
     */
    public ClearRow(int linesRemoved, int[][] newMatrix, int scoreBonus) {
        this.linesRemoved = linesRemoved;
        this.newMatrix = newMatrix;
        this.scoreBonus = scoreBonus;
    }

    /**
     * Returns the number of lines removed.
     * 
     * @return the number of cleared lines
     */
    public int getLinesRemoved() {
        return linesRemoved;
    }

    /**
     * Returns a copy of the new board matrix after clearing lines.
     * 
     * @return a 2D integer array representing the updated board matrix
     */
    public int[][] getNewMatrix() {
        return MatrixOperations.copy(newMatrix);
    }

    /**
     * Returns the score bonus awarded for clearing lines.
     * 
     * @return the score bonus
     */
    public int getScoreBonus() {
        return scoreBonus;
    }
}
