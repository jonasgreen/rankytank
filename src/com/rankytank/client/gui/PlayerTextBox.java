package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class PlayerTextBox extends TextBox{

    public PlayerTextBox() {
        setWidth("286px");
        setHeight("20px");
        getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        getElement().getStyle().setFontSize(14, Style.Unit.PX);
        getElement().getStyle().setPadding(4, Style.Unit.PX);
        getElement().getStyle().setPaddingLeft(10, Style.Unit.PX);
        getElement().getStyle().setColor("grey");


    }
}
