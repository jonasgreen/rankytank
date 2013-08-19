package com.rankytank.client.model;

import java.io.Serializable;

/**
 *
 */
public class Match implements Serializable{
    private static final long serialVersionUID = 3523423996506099301L;

    private int[] homePlayers;
    private int[] outPlayers;

    private int[] homeScore;
    private int[] outScore;


    private Match() {
    }

    public Match(int[] homePlayers, int[] outPlayers, int[] homeScore, int[] outScore) {
        this.homePlayers = homePlayers;
        this.outPlayers = outPlayers;
        this.homeScore = homeScore;
        this.outScore = outScore;
    }

    public int[] getHomePlayers() {
        return homePlayers;
    }

    public int[] getOutPlayers() {
        return outPlayers;
    }

    public int[] getHomeScore() {
        return homeScore;
    }

    public int[] getOutScore() {
        return outScore;
    }
}
