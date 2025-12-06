package com.comp2042;

public class HighScore implements Comparable<HighScore> {
    private final String name;
    private final int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }
    public int getScore() { return score; }

    @Override
    public int compareTo(HighScore other) {
        return Integer.compare(other.score, this.score);
    }

    @Override
    public String toString() {
        return name + "," + score;
    }

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
