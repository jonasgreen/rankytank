package com.rankytank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;


public class App implements EntryPoint {

    public void onModuleLoad() {
        FlowPanel p = new FlowPanel();
        p.add(new Button("wuuhuhuhuh"));
        RootLayoutPanel.get().add(p);
    }


}
