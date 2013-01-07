package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.rankytank.client.model.Player;

import java.util.List;

/**
 *
 */
public class RankingUI extends FlowPanel{

    public RankingUI(List<Player> players) {

        getElement().getStyle().setPadding(10, Style.Unit.PX);
        setWidth("600px");
        int i = 1;
        for (Player p : players) {
            addPlayer(p, i++);
        }
    }

    private void addPlayer(Player p, int number) {
        FlowPanel fRow = new FlowPanel();
        fRow.setStyleName("rank_row");
        if(number == 5){
            fRow.getElement().getStyle().setProperty("borderBottom", "1px solid green");
        }

        //number
        FlowPanel fNumber = new FlowPanel();
        fNumber.setStyleName("rank_row_number");
        fRow.add(fNumber);

        Label l = new Label(number+"");
        l.setStyleName("rank_row_number_label");
        fNumber.add(l);

        //name
        FlowPanel fName = new FlowPanel();
        fName.setStyleName("rank_row_name");
        fRow.add(fName);

        l = new Label(p.getName());
        l.setStyleName("rank_row_name_label");
        fName.add(l);

        //name
        FlowPanel fPts = new FlowPanel();
        fPts.setStyleName("rank_row_pts");
        fRow.add(fPts);

        l = new Label(p.getRank()+"");
        l.setStyleName("rank_row_pts_label");
        fPts.add(l);

        add(fRow);


    }
}
