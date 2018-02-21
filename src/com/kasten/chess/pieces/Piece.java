package com.kasten.chess.pieces;

import java.util.List;

// we can design a piece interface that gets implemented

// we might even want an abstract class ChessPiece or something like that to add constuctor and Observable

// maybe have the Piece be observable, and have the Board be observer
// to make a move... Player.movePiece([2,3], [4,5])
// this fires a method on the piece to see if it's a legal move
// if the move is illegal, throw and catch an exception
// if the move is legal, change the position and notifySubscribers, which updates the board...
// the board then directly sets the boardState in App, which updates the GUI and triggers a re-render of the board

public interface Piece {
    int getID();
    String getType();
    int getOwner();
    int getRow();
    int getColumn();
    void setPosition(int row, int col);
    List getAvailableMoves();
    boolean isAlive();
    void kill();


}
