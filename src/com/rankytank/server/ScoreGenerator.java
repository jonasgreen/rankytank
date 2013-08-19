package com.rankytank.server;

import java.util.*;

/**
 *
 */
public class ScoreGenerator {

    private int[] numberOfSetPossibilities = new int[]{1,3,5};

    private Random random = new Random();
    private static ScoreGenerator instance;

    public static ScoreGenerator get() {
        if (instance == null) {
            instance = new ScoreGenerator();
        }
        return instance;
    }

    private ScoreGenerator() {
    }


    public int[] createScores(){
        return getDistinctScores();
    }

    public int[] createScoresFrom(int[] scores){
        int[] newScores = new int[scores.length];
        int i = 0;
        boolean newScoreWin = false;
        for (int score : scores) {
            newScores[i++] = newScoreWin ? score+1 : score-1;
            newScoreWin = !newScoreWin;
        }
        return newScores;
    }



    private int[] getDistinctScores(){
        int numberOfSets = numberOfSetPossibilities[random.nextInt(numberOfSetPossibilities.length)];
        int[] sets = new int[numberOfSets];
        int i = 0;
        while (i < numberOfSets){
            sets[i++] = random.nextInt(9) + 1;
        }
        return sets;

    }



}
