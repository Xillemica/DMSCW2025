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
        String[] p = line.split(",");
        return new HighScore(p[0], Integer.parseInt(p[1]));
    }
}
