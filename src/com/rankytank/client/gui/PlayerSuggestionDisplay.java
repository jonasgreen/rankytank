package com.rankytank.client.gui;

import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public class PlayerSuggestionDisplay extends SuggestBox.DefaultSuggestionDisplay {

    private String[] nonSuggestions;

    public PlayerSuggestionDisplay(String[] nonSuggestions) {
        this.nonSuggestions = nonSuggestions;
        getPopupPanel().setStyleName("playerSuggestionDisplay");
        getPopupPanel().setWidth("300px");
    }

    @Override
    protected void showSuggestions(final SuggestBox suggestBox,
                                   Collection<? extends SuggestOracle.Suggestion> suggestions,
                                   boolean isDisplayStringHTML, boolean isAutoSelectEnabled,
                                   final SuggestBox.SuggestionCallback callback) {

        final String name = suggestBox.getText();
        if(isNonSuggestion(name)){
            return;
        }
        if(isContainedInSuggestions(suggestions, name) || isEmpty(name)){
            super.showSuggestions(suggestBox, suggestions, isDisplayStringHTML, isAutoSelectEnabled, callback);
            return;
        }

        final String createString = "Add <strong>\""+name+"\"</strong> to ranking list";
        List<SuggestOracle.Suggestion> l = new ArrayList<SuggestOracle.Suggestion>(suggestions);

        SuggestOracle.Suggestion s = new SuggestOracle.Suggestion() {
            public String getDisplayString() {
                return createString;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public String getReplacementString() {
                return name;
            }
        };

        l.add(s);
        super.showSuggestions(suggestBox, l, isDisplayStringHTML, isAutoSelectEnabled, callback);
    }

    private boolean isNonSuggestion(String name) {
        for (String nonSuggestion : nonSuggestions) {
            if(nonSuggestion.equals(name)){
                return true;
            }
        }
        return false;
    }

    private boolean isEmpty(String name) {
        return name == null || name.trim().equals("");
    }

    private boolean isContainedInSuggestions(Collection<? extends SuggestOracle.Suggestion> suggestions, String name){
        for (SuggestOracle.Suggestion s : suggestions) {
            if(s.getReplacementString().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
