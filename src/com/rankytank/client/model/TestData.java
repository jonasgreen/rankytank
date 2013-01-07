package com.rankytank.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TestData {

    public static List<Player> getPlayers(){
        List<Player> list = new ArrayList<Player>();

        list.add(new Player("John Harris", 2010));
        list.add(new Player("James White", 1790));
        list.add(new Player("Sandra Turner", 1888));
        list.add(new Player("Steven Thompson", 1580));
        list.add(new Player("Thomas Garcia", 1300));
        list.add(new Player("Charles Parker", 1160));
        list.add(new Player("David More", 1030));


        return list;
    }
}
