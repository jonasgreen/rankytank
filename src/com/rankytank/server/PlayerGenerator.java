package com.rankytank.server;

import java.util.*;

/**
 *
 */
public class PlayerGenerator {

    private Map<Integer, String> players = new HashMap<Integer, String>();
    private Random random;
    private static PlayerGenerator instance;

    public static PlayerGenerator get() {
        if (instance == null) {
            instance = new PlayerGenerator();
        }
        return instance;
    }

    private PlayerGenerator() {

    }

    public void setup(int numberOfPlayers) {
        String PRE_NAME = "Player Name ";
        players = new HashMap<Integer, String>();
        int i = 0;

        while (i < numberOfPlayers){
            players.put(i++, PRE_NAME + i);
        }

        random = new Random(numberOfPlayers);
    }

    public int[] getTwoDistinctPlayerIds(){
        return getDistinctPlayers(2, new int[]{});
    }

    public int[] getTwoDistinctPlayerIds(int[] usedPlayers){
        return getDistinctPlayers(2, usedPlayers);
    }



    private int[] getDistinctPlayers(int numberOfPlayers, int[] alreadyUsedIds){
        int[] playerIds = new int[numberOfPlayers];

        Set<Integer> usedPlayerIds = new HashSet<Integer>();
        for (int usedId : alreadyUsedIds) {
            usedPlayerIds.add(usedId);
        }

        int i = 0;
        while (i < numberOfPlayers){
            int next = random.nextInt(players.size());
            if(!usedPlayerIds.contains(next)){
                usedPlayerIds.add(next);
                playerIds[i++] = next;
            }
        }
        return playerIds;

    }



}
