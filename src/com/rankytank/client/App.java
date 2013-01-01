package com.rankytank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.rankytank.client.gui.AddResultPopUp;
import com.rankytank.client.gui.ChosenPlayer;
import com.rankytank.client.gui.PlayerSuggetsBox;
import com.rankytank.client.gui.TeamUI;


public class App implements EntryPoint {

    public void onModuleLoad() {
        FlowPanel p = new FlowPanel();

        final MultiWordSuggestOracle or = new MultiWordSuggestOracle();
        or.add("Jonas Green");
        or.add("Anders Matthesen");
        or.add("Jonas Andersen");
        or.add("Jonas Frank");
        or.add("Jonas John");
        or.add("Jon Green");


        //p.add(ui);
        RootLayoutPanel.get().add(p);

        Button b = new Button("Add result");
        b.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                AddResultPopUp pop = new AddResultPopUp(or);
                pop.show();
            }
        });

        p.add(b);
        b.setStyleName("colorbutton3");
        b.getElement().getStyle().setMargin(100, Style.Unit.PX);


    }

    private void doShowMatch() {

    }


}
