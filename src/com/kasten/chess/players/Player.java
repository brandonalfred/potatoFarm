package com.kasten.chess.players;

// we should make Player an interface
// then have a human and a robot class that implement the interface

import java.util.List;

public interface Player {
    String getType();
    List getMove();
}
