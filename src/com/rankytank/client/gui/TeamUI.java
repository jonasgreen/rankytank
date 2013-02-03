package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.jg.core.client.ui.SuggestionBoxUi;
import com.jg.core.client.ui.TextBoxUi;

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
    private SuggestionBoxUi suggester;

    public TeamUI(AddResultPopUp parent, MultiWordSuggestOracle or, String[] defaultTexts, boolean homeTeam) {
        this.setWidth("310px");
        this.teamColor = homeTeam ? Colors.GREEN : Colors.BLUE;
        this.homeTeam = homeTeam;
        this.parent = parent;
        this.oracle = or;
        this.suggestionStartTexts = defaultTexts;
        setStyleName("teamUI");
        add(chosenPlayersPanel);

        add(getSuggester());
        //getSuggestBox().getElement().getStyle().setMarginBottom(10, Style.Unit.PX);
        chosenPlayersPanel.setStyleName("chosenPlayerPanel");
    }

    public SuggestionBoxUi getSuggester() {
        if (suggester == null) {
            suggester = new SuggestionBoxUi(getSuggestBox(), suggestionStartTexts[0]);

        }
        return suggester;
    }

    public PlayerSuggetsBox getSuggestBox() {
        if (suggestBox == null) {
            suggestBox = new PlayerSuggetsBox(oracle, suggestionStartTexts, homeTeam);
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
        styleSuggestionBox();
    }

    private void keyDownInSuggestBox(KeyDownEvent event) {
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
        }
    }


    private void styleEmptySuggestionBox() {
        if(chosenPlayerUIs.isEmpty()){
            getSuggester().setEmptyText(suggestionStartTexts[0]);
        }
        else if(chosenPlayerUIs.size() == 1){
            getSuggester().setEmptyText(suggestionStartTexts[1]);
        }
        else{
            getSuggester().setEmptyText(suggestionStartTexts[2]);
        }
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

    public void styleNormal(ChosenPlayer p){
        Style style = p.getLabel().getElement().getStyle();
        style.setColor(getTeamColor());
        chosenPlayersPanel.getElement().getStyle().setBackgroundColor("white");
    }

    public void styleWon(ChosenPlayer p){
        Style style = p.getLabel().getElement().getStyle();
        style.setColor(getTeamColor());
        chosenPlayersPanel.getElement().getStyle().setBackgroundColor("#ffb200");

    }

    public void styleLost(ChosenPlayer p){
        Style style = p.getLabel().getElement().getStyle();
        style.setColor("grey");
        chosenPlayersPanel.getElement().getStyle().setBackgroundColor("white");
    }

}
