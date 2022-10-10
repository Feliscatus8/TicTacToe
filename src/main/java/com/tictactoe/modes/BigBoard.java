package com.tictactoe.modes;

import com.tictactoe.exception.SpaceTakenException;

import java.io.Serializable;

public class BigBoard implements BoardSettings, Serializable {
    private final String[][] board = {{" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "}};
    private final static int MAX_MOVES = 100;
    private final static int MINIMUM_INDEX = 0;
    private final static int MAXIMUM_INDEX = 9;
    private final static int REQUIRED_TO_WIN = 4;

    public void addMove(int row, int column, String symbol) throws SpaceTakenException {
        if (!board[row][column].equals(" ")){
            throw new SpaceTakenException("The space number " + row + ", " + column + " is already taken");
        }
        board[row][column] = symbol;
    }
    public boolean victory(int row, int column, String symbol) {
        int horizontal = 0;
        int vertical = 0;
        int diagonalLeft = 0;
        int diagonalRight = 0;

        boolean upStillCounting = true;
        boolean downStillCounting = true;
        boolean leftStillCounting = true;
        boolean rightStillCounting = true;
        boolean upLeftStillCounting = true;
        boolean downRightStillCounting = true;
        boolean downLeftStillCounting = true;
        boolean upRightStillCounting = true;

        int currentRowUp = row;
        int currentRowDown = row;
        int currentColumnLeft = column;
        int currentColumnRight = column;
        while (upStillCounting || downStillCounting || leftStillCounting || rightStillCounting || upLeftStillCounting ||
                downRightStillCounting || downLeftStillCounting || upRightStillCounting) {
            if (currentColumnLeft > MINIMUM_INDEX) {
                currentColumnLeft--;
            } else {
                leftStillCounting = false;
                upLeftStillCounting = false;
                downLeftStillCounting = false;
            }
            if (currentColumnRight < MAXIMUM_INDEX) {
                currentColumnRight++;

            }else {
                rightStillCounting = false;
                upRightStillCounting = false;
                downRightStillCounting = false;
            }

            if (currentRowUp > MINIMUM_INDEX) {
                currentRowUp--;
            }else {
                upStillCounting = false;
                upLeftStillCounting = false;
                upRightStillCounting = false;
            }
            if (currentRowDown < MAXIMUM_INDEX) {
                currentRowDown++;
            } else {
                downStillCounting = false;
                downLeftStillCounting = false;
                downRightStillCounting = false;
            }

            if (leftStillCounting && board[row][currentColumnLeft].equals(symbol)) {
                horizontal++;
            } else leftStillCounting = false;
            if (rightStillCounting && board[row][currentColumnRight].equals(symbol)) {
                horizontal++;
            } else rightStillCounting = false;
            if (upStillCounting && board[currentRowUp][column].equals(symbol)) {
                vertical++;
            } else upStillCounting = false;
            if (downStillCounting && board[currentRowDown][column].equals(symbol)) {
                vertical++;
            } else downStillCounting = false;
            if (upLeftStillCounting && board[currentRowUp][currentColumnLeft].equals(symbol)){
                diagonalLeft ++;
            } else upLeftStillCounting = false;
            if (downRightStillCounting && board[currentRowDown][currentColumnRight].equals(symbol)){
                diagonalLeft ++;
            } else downRightStillCounting = false;
            if (downLeftStillCounting && board[currentRowDown][currentColumnLeft].equals(symbol)){
                diagonalRight ++;
            } else downLeftStillCounting = false;
            if (upRightStillCounting && board[currentRowUp][currentColumnRight].equals(symbol)){
                diagonalRight ++;
            } else upRightStillCounting = false;
        }
        return horizontal >= REQUIRED_TO_WIN || vertical >= REQUIRED_TO_WIN || diagonalLeft >= REQUIRED_TO_WIN ||
                diagonalRight >= REQUIRED_TO_WIN;
    }

    @Override
    public int getMaxMoves() {
        return MAX_MOVES;
    }

    public String[][] getBoard() {
        return board;
    }
}
