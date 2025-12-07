package com.comp2042;

/**
 * Represents a player's score entry in the game.
 * <p>
 * Contains the player's name and their score, and provides
 * methods for comparison, string conversion, and parsing from a string.
 * Implements {@link Comparable} to allow sorting by score in descending order.
 */
public class HighScore implements Comparable<HighScore> {

    /** The name of the player. */
    private final String name;

    /** The score of the player. */
    private final int score;

    /**
     * Constructs a new {@code HighScore} with the specified player name and score.
     *
     * @param name the player's name
     * @param score the player's score
     */
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's score.
     *
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Compares this high score to another, for sorting purposes.
     * <p>
     * Sorting is in descending order of score.
     *
     * @param other the other high score to compare to
     * @return a negative integer, zero, or a positive integer as this score
     *         is greater than, equal to, or less than the other score
     */
    @Override
    public int compareTo(HighScore other) {
        return Integer.compare(other.score, this.score);
    }

    /**
     * Returns a string representation of the high score in the format "name,score".
     *
     * @return a string representation of this high score
     */
    @Override
    public String toString() {
        return name + "," + score;
    }

    /**
     * Parses a {@code HighScore} from a string in the format "name,score".
     * <p>
     * If the string is null, blank, or invalid, a default high score with
     * name "Unknown" and score 0 is returned.
     *
     * @param line the string to parse
     * @return a {@code HighScore} object
     */
    public static HighScore fromString(String line) {
        if (line == null || line.isBlank()) {
            return new HighScore("Unknown", 0);
        }

        String[] p = line.split(",");
        String name = p.length > 0 ? p[0].trim() : "Unknown";

        int score = 0;
        if (p.length > 1) {
            try {
                score = Integer.parseInt(p[1].trim());
            } catch (NumberFormatException e) {
                score = 0;
            }
        }

        return new HighScore(name, score);
    }
}
