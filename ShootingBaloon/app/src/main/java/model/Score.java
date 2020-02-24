package model;

import java.io.Serializable;

public class Score implements Serializable {
    String score;
    String playerName;

    public Score() {
    }

    public Score(String score, String playerName) {
        score = score;
        this.playerName = playerName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return this.playerName+"\n"+this.score;
    }
}
