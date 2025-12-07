package com.comp2042;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a list of high scores for the game.
 * <p>
 * Handles loading scores from a file, saving scores to a file,
 * adding new scores, and maintaining only the top 5 high scores.
 */
public class Scoreboard {

    /** The file name used to store scores. */
    private static final String FILE_NAME = "scores.txt";

    /** The list of high scores, sorted in descending order. */
    private List<HighScore> scores = new ArrayList<>();

    /**
     * Constructs a new {@code Scoreboard} and loads existing scores from the file.
     */
    public Scoreboard() {
        loadScores();
    }

    /**
     * Returns the current list of high scores.
     *
     * @return a list of {@link HighScore} objects
     */
    public List<HighScore> getScores() {
        return scores;
    }

    /**
     * Adds a new high score to the scoreboard.
     * <p>
     * The list is sorted in descending order and trimmed to the top 5 scores.
     * The updated list is then saved to the file.
     *
     * @param score the {@link HighScore} to add
     */
    public void addScore(HighScore score) {
        scores.add(score);
        Collections.sort(scores);
        if (scores.size() > 5) {
            scores = scores.subList(0, 5);
        }
        saveScores();
    }

    /**
     * Loads scores from the file into the scoreboard.
     * <p>
     * Existing scores in memory are cleared before loading.
     */
    private void loadScores() {
        scores.clear();
        File file = new File(FILE_NAME);

        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(HighScore.fromString(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading scores: " + e.getMessage());
        }

        Collections.sort(scores);
    }

    /**
     * Saves the current list of high scores to the file.
     */
    public void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (HighScore score : scores) {
                writer.write(score.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }
}