package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.jg.core.client.model.Result;
import com.jg.core.client.model.SetEditorListener;
import com.jg.core.client.model.SetEditorState;
import com.jg.core.client.style.Name;
import com.jg.core.client.style.StyleIt;
import com.jg.core.client.ui.PopupUI;
import com.jg.core.client.ui.SetEditorUI;

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

    public AddResultPopUp(MultiWordSuggestOracle oracle) {
        super();
        this.oracle = oracle;
        add(content);
        setWidth("680px");
        content.add(getTeamHome());
        content.add(getTeamOut());
        content.getElement().getStyle().setClear(Style.Clear.BOTH);
        content.add(getSetEditor());
        content.getElement().getStyle().setBackgroundColor("rgb(231,231,231)");

        content.add(getButtonPanel());
        setStyleName("lineShadow");
        addStyleName("popup");
    }

    public FlowPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new FlowPanel();
            buttonPanel.getElement().getStyle().setPosition(Style.Position.FIXED);
            buttonPanel.add(getCancelButton());
            buttonPanel.add(getOkButton());
            buttonPanel.setStyleName("lineShadow");
        }
        return buttonPanel;
    }

    public Button getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new Button("Cancel");
            cancelButton.setStyleName("colorbutton4");
        }
        return cancelButton;
    }

    public Button getOkButton() {
        if (okButton == null) {
            okButton = new Button("Add result");
            okButton.setStyleName("colorbutton4");
        }
        return okButton;
    }

    public SetEditorUI getSetEditor() {
        if (setEditor == null) {
            setEditor = new SetEditorUI();
            setEditor.getTextBox().setWidth("650px");
            setEditor.getTextBox().getElement().getStyle().setMargin(10, Style.Unit.PX);
            setEditor.getTextBox().getElement().getStyle().setColor("rgb(51,51,51)");
            setEditor.addListener(new SetEditorListener() {
                public void onChange(SetEditorEvent event) {
                    editorEvent(event);
                }
            });
        }
        return setEditor;
    }

    private void editorEvent(SetEditorListener.SetEditorEvent event) {
        styleTeams();
        /*if (event.getNewState() == SetEditorState.State.unknown) {
            matchIsUnknown();
        }
        else if (event.getNewState() == SetEditorState.State.draw) {
            matchIsDraw();
        }
        else if (event.getNewState() == SetEditorState.State.outIsWinning) {
            matchOutIsWinning();
        }
        else if (event.getNewState() == SetEditorState.State.homeIsWinning) {
            matchHomeIsWinning();
        }
        else if(event.getNewState() == SetEditorState.State.reset){
            matchIsReset();
        }
        else {
            matchIsIllegal();
        }
        */
    }


    public void setFocus(boolean b) {
        //setEditor.setFocus(true);
    }

    public void reset() {
    }


    public void matchHomeIsWinning() {
        reset();
        //StyleIt.add(getTeamNameOut(), playersLooseLayout);

        //getSaveButton().getButton().setEnabled(true);
        //getErrorMessage().setText("");
        //StyleIt.add(getErrorMessage(), new TextLayout().colorBaseDark());

    }


    public void matchIsReset() {
        reset();
       // getSaveButton().getButton().setEnabled(true);
    }


    public void matchOutIsWinning() {
        reset();
        //StyleIt.add(getTeamNameHome(), playersLooseLayout);
        //getSaveButton().getButton().setEnabled(true);

        //getErrorMessage().setText("");
        //StyleIt.add(getErrorMessage(), new TextLayout().colorBaseDark());
    }

    public void matchIsUnknown() {
        reset();
        //stateErrorMessage = "Scoresheet indicates a non finished match";
    }

    public void matchIsDraw() {
        reset();
        /*if (drawAllowed) {
            //ok - ignore
        }
        else {
            stateErrorMessage = "Draw is not allowed";
            getErrorMessage().setText(stateErrorMessage);
            getSaveButton().getButton().setEnabled(false);
        }
        */
    }

    public void matchIsIllegal() {
        reset();
      /*  getSaveButton().getButton().setEnabled(false);

        StyleIt.add(setEditor, illegalLayout);
        stateErrorMessage = "Scoresheet is illegal";
        getErrorMessage().setText(stateErrorMessage);
        */
    }







    @Override
    public void show(){
        super.show();
        getTeamHome().getSuggestBox().setFocus(true);
        style();

    }

    public void style(){
        layoutButtonPanel();
        styleTeams();
    }

    private void styleTeams() {
        Result result = getSetEditor().getResult();
        if(result == null){
            styleNormal();
        }
        else if(result.homeIsWinning()){
            getTeamHome().styleWon();
            getTeamOut().styleLost();
            styleOkButtonWon(getTeamHome().getTeamColor());
        }
        else if(result.outIsWinning()){
            getTeamHome().styleLost();
            getTeamOut().styleWon();
            styleOkButtonWon(getTeamOut().getTeamColor());
        }
        else {
            styleNormal();
        }
    }

    private void styleOkButtonWon(String backgroundColor){
        getOkButton().getElement().getStyle().setColor(backgroundColor);
    }

    private void styleNormal(){
        getTeamHome().styleNormal();
        getTeamOut().styleNormal();
        getOkButton().setStyleName("colorbutton4");
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
            teamHome = new TeamUI(this, oracle, new String[]{"Enter players name", "Add any teammates", "Add another teammate"}, true);
            teamHome.getElement().getStyle().setFloat(Style.Float.LEFT);
            //teamHome.getElement().getStyle().setProperty("borderRight", "1px solid grey");
            teamHome.getElement().getStyle().setBackgroundColor("rgb(231,231,231)");

        }
        return teamHome;
    }

    public TeamUI getTeamOut() {
        if (teamOut == null) {
            teamOut = new TeamUI(this, oracle, new String[]{"Enter opponents name", "Add any opponent teammates", "Add another opponent teammate"}, false);
            teamOut.getElement().getStyle().setFloat(Style.Float.RIGHT);
            teamOut.getElement().getStyle().setBackgroundColor("rgb(231,231,231)");
        }
        return teamOut;
    }
}
