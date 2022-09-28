package com.tictactoe.modes;

import com.tictactoe.exception.SpaceTakenException;

public interface BoardSettings {
    public void addMove(int row, int column, String symbol) throws SpaceTakenException;
    public boolean victory(int row, int column, String symbol);
    public int getMaxMoves();
    public String[][] getBoard();
}
