package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.jg.core.client.model.SetEditorState;
import com.jg.core.client.ui.SetEditorUI;

/**
 *
 */
public class AddResultError extends FlowPanel{


    private Label label;
    private SetEditorUI setEditorUI;

    public AddResultError(SetEditorUI setEditorUI) {
        this.setEditorUI = setEditorUI;
        add(getLabel());
    }

    public void clear(){
        getLabel().setText("");
        setEditorUI.getTextBox().getElement().getStyle().setBorderWidth(0, Style.Unit.PX);
    }

    public void setError(SetEditorState.State state){
        setEditorUI.getTextBox().getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
        setEditorUI.getTextBox().getElement().getStyle().setBorderColor("red");
    }


    public Label getLabel() {
        if (label == null) {
            label = new Label();
        }
        return label;
    }
}
