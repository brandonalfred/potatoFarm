package com.kasten.chess.utilities;

import com.kasten.chess.containers.App;
import com.kasten.chess.views.BoardWindow;
import com.kasten.chess.views.MenuWindow;
import com.kasten.chess.views.OptionsWindow;

import java.util.HashMap;

public class GUI {
    private App myApp;
    private String currentView;

    public GUI (App app) {
        myApp = app;
        currentView = "";
    }

    public void updateView(HashMap<String, String> newState) {
        String newView = newState.get("view");
        if (!newView.equals(currentView)) {
            System.out.println("new view: " + newView); // FOR DEBUGGING
            if (newView.equals("options")) new OptionsWindow(this, newState);
            if (newView.equals("main")) new MenuWindow(this, newState);
            if (newView.equals("board")) myApp.startNewGame(new BoardWindow(this, newState));
        }
    }

    public void setView(String newView) {
        myApp.setView(newView);
    }

    public void setOptions(HashMap<String, String> selectedOptions) {
        myApp.setOptions(selectedOptions);
    }

    public void setSelected(String selected) {
        // sets the selected Cell
        myApp.setSelected(selected);
    }

    public void confirmMove() {
        myApp.confirmMove();
    }
}