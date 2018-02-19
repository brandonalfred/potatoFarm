package com.kasten.chess.players;

// we should make Player an interface
// then have a human and a robot class that implement the interface

public class Player {
    private String playerType;

    public Player(String type) {
        playerType = type;
    }

    public String getType() {
        return playerType;
    }
}
