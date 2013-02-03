package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.jg.core.client.model.SetEditorListener;
import com.jg.core.client.model.SetEditorState;
import com.jg.core.client.ui.PopupUI;
import com.jg.core.client.ui.SetEditorUI;
import com.jg.core.client.ui.TextBoxUi;

/**
 *
 */
public class AddResultPopUp extends PopupUI {

    private TeamUI teamHome;
    private TeamUI teamOut;

    private SetEditorUI setEditor;

    private MultiWordSuggestOracle oracle;
    private FlowPanel content = new FlowPanel();
    private FlowPanel buttonPanel;
    private Button cancelButton;
    private Button okButton;
    private AddResultError errorPanel;

    public AddResultPopUp(MultiWordSuggestOracle oracle) {
        super();
        this.oracle = oracle;
        add(content);
        setWidth("680px");

        FlowPanel fp = new FlowPanel();
        fp.setHeight("24px");
        fp.getElement().getStyle().setBackgroundColor("black");
        Label w = new Label("New match");
        fp.add(w);
        fp.getElement().getStyle().setBorderColor("black");
        fp.getElement().getStyle().setPadding(8, Style.Unit.PX);
        fp.getElement().getStyle().setPaddingBottom(0, Style.Unit.PX);
        w.getElement().getStyle().setColor("white");
        w.getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);

        content.add(fp);
        content.add(getTeamHome());
        content.add(getTeamOut());

        content.add(getSetEditor());
        content.add(getButtonPanel());
        setStyleName("lineShadow");
        addStyleName("popup");
    }



    public FlowPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new FlowPanel();
            buttonPanel.getElement().getStyle().setPosition(Style.Position.FIXED);
            buttonPanel.add(getOkButton());
            buttonPanel.add(getCancelButton());
            buttonPanel.setStyleName("lineShadow");
        }
        return buttonPanel;
    }

    public Button getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new Button("Cancel");
            cancelButton.setStyleName("colorbutton4");
            cancelButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                     AddResultPopUp.this.hide();
                }
            });
        }
        return cancelButton;
    }

    public Button getOkButton() {
        if (okButton == null) {
            okButton = new Button("Add match");
            okButton.setStyleName("colorbutton4");
            okButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    addResult();
                }
            });
        }
        return okButton;
    }


    private void addResult() {
        SetEditorState.State state = getSetEditor().getState();
        if(isPersistableState(state)){
            persistResult();
        }
    }

    private boolean isPersistableState(SetEditorState.State state) {
        return state == SetEditorState.State.draw || state == SetEditorState.State.homeIsWinning || state == SetEditorState.State.outIsWinning;
    }

    private void persistResult() {

    }

    public SetEditorUI getSetEditor() {
        if (setEditor == null) {
            setEditor = new SetEditorUI();
            setEditor.getTextBox().setWidth("650px");
            setEditor.getElement().getStyle().setPadding(5, Style.Unit.PX);
            setEditor.getElement().getStyle().setPaddingBottom(15, Style.Unit.PX);

            setEditor.addListener(new SetEditorListener() {
                public void onChange(SetEditorEvent event) {
                    handleState(event.getNewState());
                }
            });
            setEditor.getElement().getStyle().setClear(Style.Clear.BOTH);
            setEditor.getElement().getStyle().setProperty("borderTop", "1px solid rgb(201,201,201)");

        }
        return setEditor;
    }

    private void handleState(SetEditorState.State state){
        if (state == SetEditorState.State.unknown) {
            styleNormal();
        }
        else if (state == SetEditorState.State.draw) {
            styleNormal();
        }
        else if (state == SetEditorState.State.outIsWinning) {
            styleOutIsWinning();
        }
        else if (state == SetEditorState.State.homeIsWinning) {
            styleHomeIsWinning();
        }
        else if(state == SetEditorState.State.reset){
            styleNormal();
        }
        else {
            styleNormal();
        }
    }


    public void setFocus(boolean b) {
        //setEditor.setFocus(true);
    }

    public void reset() {
    }

    @Override
    public void show(){
        super.show();
        getTeamHome().getSuggestBox().setFocus(true);
        style();
    }

    public void style(){
        layoutButtonPanel();
         handleState(getSetEditor().getState());
    }

    private void styleOutIsWinning() {
        getTeamHome().styleLost();
        getTeamOut().styleWon();
        styleOkButtonWon(getTeamOut().getTeamColor());
    }

    private void styleHomeIsWinning() {
        getTeamHome().styleWon();
        getTeamOut().styleLost();
        styleOkButtonWon(getTeamHome().getTeamColor());
    }

    private void styleOkButtonWon(String color){
        getOkButton().setStyleName("colorbutton3");
        getOkButton().getElement().getStyle().setColor(color);
    }

    private void styleNormal(){
        getTeamHome().styleNormal();
        getTeamOut().styleNormal();
        getOkButton().setStyleName("colorbutton4");
        getOkButton().getElement().getStyle().setColor("#333333");
    }

    private void layoutButtonPanel() {
        int top = getElement().getAbsoluteTop();
        int clientHeight = getElement().getClientHeight();
        int left = getElement().getAbsoluteLeft();
        int width = getElement().getClientWidth();

        getButtonPanel().getElement().getStyle().setTop(top + clientHeight + 15, Style.Unit.PX);
        getButtonPanel().getElement().getStyle().setLeft(left + width - getButtonPanel().getElement().getClientWidth()+2, Style.Unit.PX);
    }

    public TeamUI getTeamHome() {
        if (teamHome == null) {
            teamHome = new TeamUI(this, oracle, new String[]{"Players name", "Add any teammates", "Add another teammate"}, true);
            teamHome.getElement().getStyle().setFloat(Style.Float.LEFT);
            //teamHome.getElement().getStyle().setProperty("borderRight", "1px solid grey");
            //teamHome.getElement().getStyle().setBackgroundColor("rgb(231,231,231)");

        }
        return teamHome;
    }

    public TeamUI getTeamOut() {
        if (teamOut == null) {
            teamOut = new TeamUI(this, oracle, new String[]{"Opponents name", "Add any opponent teammates", "Add another opponent teammate"}, false);
            teamOut.getElement().getStyle().setFloat(Style.Float.LEFT);
            //teamOut.getElement().getStyle().setBackgroundColor("rgb(231,231,231)");
        }
        return teamOut;
    }
}
