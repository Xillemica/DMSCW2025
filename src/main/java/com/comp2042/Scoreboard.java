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

public class Scoreboard {

    private static final String FILE_NAME = "scores.txt";
    private List<HighScore> scores = new ArrayList<>();

    public Scoreboard() {
        loadScores();
    }

    public List<HighScore> getScores() {
        return scores;
    }

    public void addScore(HighScore score) {
        scores.add(score);
        Collections.sort(scores);  
        if (scores.size() > 5) {
            scores = scores.subList(0, 5); 
        }
        saveScores();
    }

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
            e.printStackTrace();
        }

        Collections.sort(scores);
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (HighScore score : scores) {
                writer.write(score.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
