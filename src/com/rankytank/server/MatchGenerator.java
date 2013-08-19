package com.rankytank.server;


import com.rankytank.client.model.Match;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MatchGenerator {

    private static MatchGenerator instance;
    private List<Match> matches;

    public static MatchGenerator get() {
        if (instance == null) {
            instance = new MatchGenerator();
        }
        return instance;
    }

    private MatchGenerator() {
    }

    public List<Match> createMatches(int numberOfMatches) {
        if (matches == null || matches.size() != numberOfMatches) {
            matches = new ArrayList<Match>();

            int i = 0;
            while (i++ < numberOfMatches) {
                matches.add(createMatch());
            }
        }
        return matches;
    }

    private Match createMatch() {
        int[] homePlayerIds = PlayerGenerator.get().getTwoDistinctPlayerIds();
        int[] outPlayerIds = PlayerGenerator.get().getTwoDistinctPlayerIds(homePlayerIds);

        int[] homeScores = ScoreGenerator.get().createScores();
        int[] outScores = ScoreGenerator.get().createScoresFrom(homeScores);

        return new Match(homePlayerIds, outPlayerIds, homeScores, outScores);
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();

        PlayerGenerator.get().setup(100);
        StringBuilder sb = new StringBuilder();

        for (Match m : MatchGenerator.get().createMatches(1000)) {
            sb.append("------------");
            sb.append("(");

            int count = 0;
            for (int i : m.getHomeScore()) {
                sb.append(i).append("-").append(m.getOutScore()[count++]).append(" ");
            }

            sb.append(")\n");

        }
        System.out.println(sb.toString());
        System.out.println(System.currentTimeMillis() - start);
    }
}
