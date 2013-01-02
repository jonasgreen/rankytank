package com.rankytank.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class PlayerTextBox extends TextBox{

    public PlayerTextBox() {
        setWidth("290px");
        setHeight("20px");
        getElement().getStyle().setProperty("fontFamily", "klavika");
        getElement().getStyle().setFontStyle(Style.FontStyle.ITALIC);
        getElement().getStyle().setFontSize(14, Style.Unit.PX);
        getElement().getStyle().setPadding(4, Style.Unit.PX);
        getElement().getStyle().setPaddingLeft(10, Style.Unit.PX);
        getElement().getStyle().setPaddingRight(10, Style.Unit.PX);
        getElement().getStyle().setColor("rgb(171,171,171)");


    }
}
