package com.tictactoe.modes;

import com.tictactoe.exception.SpaceTakenException;

public class BigBoard implements BoardSettings {
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
    private final int maxMoves = 100;

    public void addMove(int row, int column, String symbol) throws SpaceTakenException {
        if (!board[row][column].equals(" ")){
            throw new SpaceTakenException("The space number " + row + ", " + column + " is already taken");
        }
        board[row][column] = symbol;
    }
    public boolean victory(int row, int column, String symbol) {
        final int minimumIndex = 0;
        final int maximumIndex = 9;
        final int requiredToWin = 4;

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
            if (currentColumnLeft > minimumIndex) {
                currentColumnLeft--;
            } else {
                leftStillCounting = false;
                upLeftStillCounting = false;
                downLeftStillCounting = false;
            }
            if (currentColumnRight < maximumIndex) {
                currentColumnRight++;

            }else {
                rightStillCounting = false;
                upRightStillCounting = false;
                downRightStillCounting = false;
            }

            if (currentRowUp > minimumIndex) {
                currentRowUp--;
            }else {
                upStillCounting = false;
                upLeftStillCounting = false;
                upRightStillCounting = false;
            }
            if (currentRowDown < maximumIndex) {
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
        return horizontal >= requiredToWin || vertical >= requiredToWin || diagonalLeft >= requiredToWin ||
                diagonalRight >= requiredToWin;
    }

    @Override
    public int getMaxMoves() {
        return maxMoves;
    }

    public String[][] getBoard() {
        return board;
    }
}
