package com.kasten.chess.model;

public class AppState {
    private String view;
    private String theme;
    private String opponent;
    private String difficulty;
    private String board;

    public AppState() {
        view = "main";
        theme = "day";
        opponent = "cpu";
        difficulty = "hard";
        board = "blank";
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }


}
