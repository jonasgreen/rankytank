package com.rankytank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.rankytank.client.gui.ChosenPlayer;
import com.rankytank.client.gui.PlayerSuggetsBox;
import com.rankytank.client.gui.TeamUI;


public class App implements EntryPoint {

    public void onModuleLoad() {
        FlowPanel p = new FlowPanel();

        MultiWordSuggestOracle or = new MultiWordSuggestOracle();
        or.add("Jonas Green");
        or.add("Anders Matthesen");
        or.add("Jonas Andersen");
        or.add("Jonas Frank");
        or.add("Jonas John");
        or.add("Jon Green");

        TeamUI ui = new TeamUI(or);

        p.add(ui);
        RootLayoutPanel.get().add(p);
        ui.getSuggestBox().setFocus(true);

    }


}
