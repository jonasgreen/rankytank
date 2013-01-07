package com.rankytank.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.rankytank.client.pages.FrontPage;
import com.rankytank.client.pages.RankingPage;


public class App implements EntryPoint {
    private static RootLayoutPanel root = RootLayoutPanel.get();
    public static Widget page;

    public static void loadPage(Widget page){
        root.add(page);

    }

    public void onModuleLoad() {
        //setPadding(50, Style.Unit.PX);
        loadPage(new RankingPage());
/*
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                loadPage(History.getToken());
            }
        });
*/
        //UrlUtil.init("rankytank");
        //loadPage(History.getToken());
    }

    private void loadPage(String token) {
        if (token == null || token.isEmpty()) {
            //loadFrontPage();
        }
        else {
            //loadListPage(token);
        }


    }

    /*
    private void createNewList() {
        OnOffService.getInstance().createOnOffList(new CallBack<SingleResult<OnOffList>>() {
            @Override
            public void success(SingleResult<OnOffList> result) {
                ListPage page = new ListPage(result);
                AppManager.loadPage(page);
                History.newItem("id=" + result.getResult().getUrl(), false);
                page.getAddPanel().getTextBox().setFocus(true);
            }

            @Override
            public void fail(Throwable t) {
                t.printStackTrace();
            }
        });
    }
    */
/*
    private void loadFrontPage(String token) {
        AppManager.loadPage(new StartPage(token));
    }

    private void loadListPage(final String token) {
        try {
            OnOffService.getInstance().getOnOffList(token.replace("id=", ""), new CallBack<SingleResult<OnOffList>>() {
                @Override
                public void success(SingleResult<OnOffList> result) {
                    if (result.getResult() == null) {
                        loadFrontPage(token);
                    }
                    else {
                        ListPage page = new ListPage(result);
                        AppManager.loadPage(page);
                        if(result.getResult().getItems().isEmpty()){
                            page.getAddPanel().getTextBox().setFocus(true);
                        }
                    }
                }

                @Override
                public void fail(Throwable t) {
                    loadFrontPage(token);
                }
            });
        }
        catch (Exception e) {
            loadFrontPage(token);
        }

    }




*/




    /*public void onModuleLoad() {
        FlowPanel p = new FlowPanel();

        final MultiWordSuggestOracle or = new MultiWordSuggestOracle();
        or.add("Jonas Green");
        or.add("Anders Matthesen");
        or.add("Jonas Andersen");
        or.add("Jonas Frank");
        or.add("Jonas John");
        or.add("Jon Green");


        //p.add(ui);
        RootLayoutPanel.get().add(p);

        Button b = new Button("Add result");
        b.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                AddResultPopUp pop = new AddResultPopUp(or);
                pop.show();
            }
        });

        p.add(new SpinnerUI());
        p.add(b);
        b.setStyleName("colorbutton3");
        b.getElement().getStyle().setMargin(100, Style.Unit.PX);


    }

    private void doShowMatch() {

    }

*/
}
