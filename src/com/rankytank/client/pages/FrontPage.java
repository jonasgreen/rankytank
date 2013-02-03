package com.rankytank.client.pages;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.rankytank.client.App;
import com.rankytank.client.gui.AddResultPopUp;
import com.rankytank.client.gui.RankingUI;
import com.rankytank.client.model.TestData;

/**
 *
 */
public class FrontPage extends FlowPanel{
    private FlowPanel top;
    private FlowPanel middle;
    private FlowPanel bottom;
    private Button createRankingButton;

    public FrontPage() {
        setWidth("900px");
        getElement().getStyle().setProperty("marginLeft", "auto");
        getElement().getStyle().setProperty("marginRight", "auto");

        add(getTop());
        add(getMiddle());
        add(getBottom());
    }

    public FlowPanel getTop() {
        if (top == null) {
            top = new FlowPanel();
            top.setHeight("50px");
        }
        return top;
    }

    public FlowPanel getMiddle() {
        if (middle == null) {
            middle = new FlowPanel();
            middle.setWidth("900px");
            Label l = new Label("Create your own ranking list");
            l.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
            l.getElement().getStyle().setFontSize(46, Style.Unit.PX);
            l.getElement().getStyle().setPaddingBottom(25, Style.Unit.PX);
            l.getElement().getStyle().setPaddingTop(200, Style.Unit.PX);


            middle.add(l);

            FlowPanel f = new FlowPanel();
            f.setWidth("219px");
            f.add(getCreateRankingButton());
            f.getElement().getStyle().setProperty("marginLeft", "auto");
            f.getElement().getStyle().setProperty("marginRight", "auto");

            middle.add(f);
        }
        return middle;
    }

    public Button getCreateRankingButton() {
        if (createRankingButton == null) {
            createRankingButton = new Button("Create ranking list");
            createRankingButton.setStyleName("colorbutton3");
            createRankingButton.getElement().getStyle().setFontSize(24, Style.Unit.PX);


        }
        return createRankingButton;
    }

    public FlowPanel getBottom() {
        if (bottom == null) {
            bottom = new FlowPanel();
        }
        return bottom;
    }

}
