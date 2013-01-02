package com.rankytank.client.gui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;

import java.util.Collection;

/**
 *
 */
public class PlayerSuggetsBox extends SuggestBox{

    public PlayerSuggetsBox(SuggestOracle or, String[] nonSuggestions, boolean homeTeam) {
        super(or, new PlayerTextBox(), new PlayerSuggestionDisplay(nonSuggestions, homeTeam));
    }


}
