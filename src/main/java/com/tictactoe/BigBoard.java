package com.tictactoe;

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

    public void addMove(int row, int column, String symbol) throws SpaceTakenException{
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
            if (currentColumnLeft > 0) {
                currentColumnLeft--;
            } else {
                leftStillCounting = false;
                upLeftStillCounting = false;
                downLeftStillCounting = false;
            }
            if (currentColumnRight < 9) {
                currentColumnRight++;

            }else {
                rightStillCounting = false;
                upRightStillCounting = false;
                downRightStillCounting = false;
            }

            if (currentRowUp > 0) {
                currentRowUp--;
            }else {
                upStillCounting = false;
                upLeftStillCounting = false;
                upRightStillCounting = false;
            }
            if (currentRowDown < 9) {
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
        return horizontal >= 4 || vertical >= 4 || diagonalLeft >= 4 || diagonalRight >= 4;
    }



    public boolean victoryOld(int row, int column, String symbol){
        int horizontal = 0;
        int currentColumn = column;
        while (true){
            if (currentColumn > 0){
                currentColumn --;
                if (board[row][currentColumn].equals(symbol)){
                    horizontal ++;
                }else break;
            }else break;
        }
        currentColumn = column;
        while (true){
            if (currentColumn < 9){
                currentColumn ++;
                if (board[row][currentColumn].equals(symbol)){
                    horizontal ++;
                }else break;
            }else break;
        }
        if (horizontal >= 4){
            return true;
        }

        int vertical = 0;
        int currentRow = row;
        while (true){
            if (currentRow > 0){
                currentRow --;
                if (board[currentRow][column].equals(symbol)){
                    vertical ++;
                }else break;
            }else break;
        }
        currentRow = row;
        while (true){
            if (currentRow < 9){
                currentRow ++;
                if (board[currentRow][column].equals(symbol)){
                    vertical ++;
                }else break;
            }else break;
        }
        if (vertical >= 4) return true;

        int diagonalLeft = 0;
        currentRow = row;
        currentColumn = column;
        while (true){
            if (currentRow > 0 && currentColumn > 0){
                currentRow --;
                currentColumn --;
                if (board[currentRow][currentColumn].equals(symbol)){
                    diagonalLeft ++;
                }else break;
            }else break;
        }
        currentRow = row;
        currentColumn = column;
        while (true){
            if (currentRow < 9 && currentColumn < 9){
                currentRow ++;
                currentColumn ++;
                if (board[currentRow][column].equals(symbol)){
                    diagonalLeft ++;
                }else break;
            }else break;
        }
        if (diagonalLeft >= 4) return true;

        int diagonalRight = 0;
        currentRow = row;
        currentColumn = column;
        while (true){
            if (currentRow < 9 && currentColumn > 0){
                currentRow ++;
                currentColumn --;
                if (board[currentRow][currentColumn].equals(symbol)){
                    diagonalRight ++;
                }else break;
            }else break;
        }
        currentRow = row;
        currentColumn = column;
        while (true){
            if (currentRow > 0 && currentColumn < 9){
                currentRow --;
                currentColumn ++;
                if (board[currentRow][column].equals(symbol)){
                    diagonalRight ++;
                }else break;
            }else break;
        }
        return (diagonalRight >= 4);
    }

    @Override
    public int getMaxMoves() {
        return maxMoves;
    }

    public String[][] getBoard() {
        return board;
    }
}
