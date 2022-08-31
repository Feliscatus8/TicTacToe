package com.tictactoe;

public class Board {
    private final String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

    public void addMove(int row, int column, String symbol) throws SpaceTakenException{
        if (!board[row][column].equals(" ")){
            throw new SpaceTakenException("The space number " + row + ", " + column + " is already taken");
        }
        board[row][column] = symbol;
    }

    public String presentBoard(){
        return "|" + board[0][0] + "|" + board[0][1] + "|" + board[0][2] + "|\n" +
                "|" + board[1][0] + "|" + board[1][1] + "|" + board[1][2] + "|\n" +
                "|" + board[2][0] + "|" + board[2][1] + "|" + board[2][2] + "|\n";
    }
    public boolean victory(int row, int column, String symbol){
        if (board[row][0].equals(symbol) && board[row][1].equals(symbol) && board[row][2].equals(symbol)) return true;
        if (board[0][column].equals(symbol) && board[1][column].equals(symbol) && board[2][column].equals(symbol)) return true;
        if (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol)) return true;
        return board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol);
    }

    public String[][] getBoard() {
        return board;
    }
}
