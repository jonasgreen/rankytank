package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class ChosenPlayer extends FlowPanel{
    private Label label;
    private FlowPanel deletePanel;
    private TeamUI teamUi;
    private String playerName;

    public ChosenPlayer(String playerName, TeamUI parent) {
        this.teamUi = parent;
        this.playerName = playerName;
        add(getLabel());

        add(getDeletePanel());
        setStyleName("chosenPlayer");


        getElement().getStyle().setClear(Style.Clear.BOTH);

        label.getElement().getStyle().setColor(parent.getTeamColor());

    }

    public Label getLabel() {
        if (label == null) {
            label = new Label(playerName);
            label.setStyleName("chosenPlayerLabel");
        }
        return label;
    }


    public FlowPanel getDeletePanel() {
        if (deletePanel == null) {
            deletePanel = new FlowPanel();
            final Label l = new Label("x");
            l.getElement().getStyle().setPaddingTop(5, Style.Unit.PX);
            l.getElement().getStyle().setPaddingRight(20, Style.Unit.PX);
            l.getElement().getStyle().setFontSize(10, Style.Unit.PX);
            l.getElement().getStyle().setColor("rgb(171,171,171)");
            l.setTitle("Delete");
            l.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    teamUi.delete(ChosenPlayer.this);

                }
            });

            l.addMouseOverHandler(new MouseOverHandler() {
                public void onMouseOver(MouseOverEvent event) {
                    getElement().getStyle().setBackgroundColor("rgb(241,241,241)");
                    l.getElement().getStyle().setCursor(Style.Cursor.POINTER);
                    l.getElement().getStyle().setColor("rgb(51,51,51)");
                }
            });

            l.addMouseOutHandler(new MouseOutHandler() {
                public void onMouseOut(MouseOutEvent event) {
                    getElement().getStyle().setBackgroundColor("white");
                    l.getElement().getStyle().setColor("rgb(171,171,171)");
                }
            });

            deletePanel.add(l);
            deletePanel.getElement().getStyle().setFloat(Style.Float.RIGHT);
            deletePanel.setWidth("20px");

        }
        return deletePanel;
    }


}
