package com.rankytank.client.pages;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.rankytank.client.gui.AddResultPopUp;
import com.rankytank.client.gui.RankingUI;
import com.jg.core.client.ui.TextBoxUi;
import com.rankytank.client.model.TestData;

/**
 *
 */
public class RankingPage extends FlowPanel {

    private RankingUI left;
    private FlowPanel top;
    private FlowPanel right;
    private Label header;
    private Button addResultButton;

    public RankingPage() {
        getElement().getStyle().setBackgroundColor("rgb(231,231,231)");

        Image image = new Image("logo.png");
        image.getElement().getStyle().setPosition(Style.Position.FIXED);
        image.getElement().getStyle().setBottom(0, Style.Unit.PX);
        image.getElement().getStyle().setRight(120, Style.Unit.PX);
        add(image);

        add(getTop());


        add(getLeft());
        add(getRight());
        setHeight("100%");
    }

    public FlowPanel getLeft() {
        if (left == null) {
            left = new RankingUI(TestData.getPlayers());
            left.setHeight("100%");
            left.setStyleName("lineShadow");
            left.getElement().getStyle().setFloat(Style.Float.LEFT);

            left.getElement().getStyle().setMargin(30, Style.Unit.PX);
            left.getElement().getStyle().setBackgroundColor("rgb(251,251,251)");

        }
        return left;
    }

    public FlowPanel getTop() {
        if (top == null) {
            top = new FlowPanel();
            top.setHeight("50px");
            top.add(getHeader());
        }
        return top;
    }

    public Label getHeader() {
        if (header == null) {
            header = new Label("Schantz' Bordfodboldrangliste");
            header.setStyleName("ranking_title");
        }
        return header;
    }

    public FlowPanel getRight() {
        if (right == null) {
            right = new FlowPanel();
            right.setWidth("300px");
            right.getElement().getStyle().setFloat(Style.Float.LEFT);
            right.getElement().getStyle().setMargin(0, Style.Unit.PX);
            right.getElement().getStyle().setMarginTop(50, Style.Unit.PX);

            right.add(getAddResultButton());
            right.add(new TextBoxUi());

        }
        return right;
    }

    public Button getAddResultButton() {
        if (addResultButton == null) {
            addResultButton = new Button("Add match");
            addResultButton.setStyleName("colorbutton3");
            addResultButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {

                    final MultiWordSuggestOracle or = new MultiWordSuggestOracle();
                    or.add("Jonas Green");
                    or.add("Anders Matthesen");
                    or.add("Jonas Andersen");
                    or.add("Jonas Frank");
                    or.add("Jonas John");
                    or.add("Jon Green");


                    AddResultPopUp popUp = new AddResultPopUp(or);
                    popUp.show();
                }
            });
        }
        return addResultButton;
    }

}
