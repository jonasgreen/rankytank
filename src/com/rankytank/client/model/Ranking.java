package com.rankytank.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Ranking {

    private String name;
    private List<Player> players = new ArrayList<Player>();

    public Ranking() {
    }

    public Ranking(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }


}
