package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TeamUI extends FlowPanel implements Navigator {

    private String[] suggestionStartTexts;
    private List<ChosenPlayer> chosenPlayerUIs = new ArrayList<ChosenPlayer>();
    private FlowPanel chosenPlayersPanel = new FlowPanel();

    private AddResultPopUp parent;
    private MultiWordSuggestOracle oracle;
    private PlayerSuggetsBox suggestBox;

    private String teamColor;
    private boolean homeTeam;

    public TeamUI(AddResultPopUp parent, MultiWordSuggestOracle or, String[] defaultTexts, boolean homeTeam) {
        this.teamColor = homeTeam ? Colors.GREEN : Colors.BLUE;
        this.homeTeam = homeTeam;
        this.parent = parent;
        this.oracle = or;
        this.suggestionStartTexts = defaultTexts;
        setStyleName("teamUI");
        add(getSuggestBox());
        getSuggestBox().getElement().getStyle().setMarginBottom(10, Style.Unit.PX);
        add(chosenPlayersPanel);
    }

    public PlayerSuggetsBox getSuggestBox() {
        if (suggestBox == null) {
            suggestBox = new PlayerSuggetsBox(oracle, suggestionStartTexts, homeTeam);
            suggestBox.getValueBox().setText(suggestionStartTexts[0]);
            suggestBox.addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent event) {
                    keyDownInSuggestBox(event);
                }
            });
            suggestBox.addKeyUpHandler(new KeyUpHandler() {
                public void onKeyUp(KeyUpEvent event) {
                    keyUpInSuggestBox(event);
                }
            });
            suggestBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
                public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {
                    playerSelected(event.getSelectedItem().getReplacementString());
                }
            });

            suggestBox.getValueBox().addFocusHandler(new FocusHandler() {
                public void onFocus(FocusEvent event) {
                    handleFocus();
                }
            });
            suggestBox.getValueBox().addBlurHandler(new BlurHandler() {
                public void onBlur(BlurEvent event) {
                    handleBlur();
                }
            });
        }
        return suggestBox;
    }

    private void keyUpInSuggestBox(KeyUpEvent event) {
        styleSuggestionBox();
    }

    private void handleBlur() {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void keyDownInSuggestBox(KeyDownEvent event) {
        if (isSuggestionStartText(getSuggestBox().getText())) {
            getSuggestBox().setText("");
        }

        int keycode = event.getNativeEvent().getKeyCode();
        if (getSuggestBox().isSuggestionListShowing()) {
            if (keycode == KeyCodes.KEY_ESCAPE) {//ESC
                getSuggestBox().hideSuggestionList();
            }
            else if (keycode == KeyCodes.KEY_UP) {//arrow up
                final String s = getSuggestBox().getText();
                if (s == null || s.equals("")) {
                    //ignore
                }
                else {
                    Timer t = new Timer() {
                        @Override
                        public void run() {
                            getSuggestBox().getValueBox().setCursorPos(s.length());
                        }
                    };
                    t.schedule(50);
                }
            }
        }
    }

    public void handleFocus() {
        styleSuggestionBox();
    }

    public void styleSuggestionBox(){
        String text = getSuggestBox().getText();
        if(text == null || text.equals("")){
            styleEmptySuggestionBox();
            getSuggestBox().hideSuggestionList();
            getSuggestBox().getValueBox().getElement().getStyle().setFontStyle(Style.FontStyle.ITALIC);
            getSuggestBox().getValueBox().getElement().getStyle().setColor("rgb(171,171,171)");
        }
        else if(isSuggestionStartText(text)){
            getSuggestBox().getValueBox().setText("");
            styleEmptySuggestionBox();
        }
        else{
            getSuggestBox().getValueBox().getElement().getStyle().setFontStyle(Style.FontStyle.NORMAL);
            getSuggestBox().getValueBox().getElement().getStyle().setColor("rgb(51,51,51)");
        }
    }

    private boolean isSuggestionStartText(String text) {
        for (String s : suggestionStartTexts) {
            if(s.equals(text)){
                return true;
            }
        }
        return false;
    }

    private void styleEmptySuggestionBox() {
        if(chosenPlayerUIs.isEmpty()){
            getSuggestBox().getValueBox().setText(suggestionStartTexts[0]);
        }
        else if(chosenPlayerUIs.size() == 1){
            getSuggestBox().getValueBox().setText(suggestionStartTexts[1]);
        }
        else{
            getSuggestBox().getValueBox().setText(suggestionStartTexts[2]);
        }
        getSuggestBox().getValueBox().setCursorPos(0);
    }


    public void playerSelected(String playerName) {
        ChosenPlayer playerUI = new ChosenPlayer(playerName, this);
        chosenPlayersPanel.add(playerUI);
        chosenPlayerUIs.add(playerUI);
        getSuggestBox().getValueBox().setText("");
        styleSuggestionBox();
        parent.style();
    }

    public void delete(ChosenPlayer w) {
        getSuggestBox().getValueBox().setFocus(false);
        chosenPlayerUIs.remove(w);
        w.removeFromParent();
        getSuggestBox().getValueBox().setFocus(true);
        parent.style();
    }

    public String getTeamColor() {
        return teamColor;
    }

    public void styleNormal(ChosenPlayer p){
        Style style = p.getElement().getStyle();
        style.setColor(teamColor);
        style.setFontWeight(Style.FontWeight.NORMAL);
    }

    public void styleWon(){
        for (ChosenPlayer p : chosenPlayerUIs) {
            styleWon(p);
        }
    }

    public void styleLost(){
        for (ChosenPlayer p : chosenPlayerUIs) {
            styleLost(p);
        }
    }


    public void styleNormal(){
        for (ChosenPlayer p : chosenPlayerUIs) {
            styleNormal(p);
        }
    }

    public void styleWon(ChosenPlayer p){
        Style style = p.getLabel().getElement().getStyle();
        style.setColor(teamColor);
        style.setFontWeight(Style.FontWeight.BOLD);
    }

    public void styleLost(ChosenPlayer p){
        Style style = p.getLabel().getElement().getStyle();
        style.setColor("grey");
        style.setFontWeight(Style.FontWeight.NORMAL);
    }

}
