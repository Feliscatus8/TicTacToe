package com.tictactoe;

public class Board implements BoardSettings {
    private final String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    private final int maxMoves = 9;

    public void addMove(int row, int column, String symbol) throws SpaceTakenException{
        if (!board[row][column].equals(" ")){
            throw new SpaceTakenException("The space number " + row + ", " + column + " is already taken");
        }
        board[row][column] = symbol;
    }

    public boolean victory(int row, int column, String symbol){
        if (board[row][0].equals(symbol) && board[row][1].equals(symbol) && board[row][2].equals(symbol)) return true;
        if (board[0][column].equals(symbol) && board[1][column].equals(symbol) && board[2][column].equals(symbol)) return true;
        if (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol)) return true;
        return board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol);
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    public String[][] getBoard() {
        return board;
    }
}
