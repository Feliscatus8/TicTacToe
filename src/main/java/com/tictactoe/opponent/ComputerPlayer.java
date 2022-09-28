package com.tictactoe.opponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    public int[] makeMove(String[][] board) {
        List<int[]> fieldsList = new ArrayList<>();
        for (int row = 0; row < board.length; row ++) {
            for (int column = 0; column < board[0].length; column ++) {
                fieldsList.add(new int[]{row, column});
            }
        }
        List<int[]> possibleMoves = new ArrayList<>();
        for(int[] field: fieldsList){
            if(board[field[0]][field[1]].equals(" ") ) possibleMoves.add(field);
        }
        Random random = new Random();
        int possibleMovesSize = possibleMoves.size();
        return possibleMoves.get(random.nextInt(possibleMovesSize));
    }
    public int[] makeMove(String[][] board, int[] lastMove, String opponentSymbol) {
        if (lastMove == null) return makeMove(board);
        final int minimumIndex = 0;
        final int maximumIndex = board.length - 1;

        int horizontal = 0;
        int vertical = 0;
        int diagonalLeft = 0;
        int diagonalRight = 0;

        List<int[]> horizontalMoves = new ArrayList<>();
        List<int[]> verticalMoves = new ArrayList<>();
        List<int[]> diagonalLeftMoves = new ArrayList<>();
        List<int[]> diagonalRightMoves = new ArrayList<>();
        List<int[]> possibleMoves = new ArrayList<>();

        boolean upStillCounting = true;
        boolean downStillCounting = true;
        boolean leftStillCounting = true;
        boolean rightStillCounting = true;
        boolean upLeftStillCounting = true;
        boolean downRightStillCounting = true;
        boolean downLeftStillCounting = true;
        boolean upRightStillCounting = true;

        int currentRowUp = lastMove[0];
        int currentRowDown = lastMove[0];
        int currentColumnLeft = lastMove[1];
        int currentColumnRight = lastMove[1];

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
            } else {
                rightStillCounting = false;
                upRightStillCounting = false;
                downRightStillCounting = false;
            }

            if (currentRowUp > minimumIndex) {
                currentRowUp--;
            } else {
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

            if (leftStillCounting) {
                if (board[lastMove[0]][currentColumnLeft].equals(opponentSymbol)) {
                    horizontal++;
                } else if (board[lastMove[0]][currentColumnLeft].equals(" ")) {
                    horizontalMoves.add(new int[]{lastMove[0], currentColumnLeft});
                    if (currentColumnLeft > minimumIndex) {
                        if (board[lastMove[0]][currentColumnLeft - 1].equals(opponentSymbol)) {
                            horizontal++;
                        }
                    }
                    leftStillCounting = false;
                } else leftStillCounting = false;
            }
            if (rightStillCounting) {
                if (board[lastMove[0]][currentColumnRight].equals(opponentSymbol)) {
                    horizontal++;
                } else if (board[lastMove[0]][currentColumnRight].equals(" ")) {
                    horizontalMoves.add(new int[]{lastMove[0], currentColumnRight});
                    if (currentColumnRight > maximumIndex) {
                        if (board[lastMove[0]][currentColumnRight + 1].equals(opponentSymbol)) {
                            horizontal++;
                        }
                    }
                    rightStillCounting = false;
                } else rightStillCounting = false;
            }
            if (upStillCounting) {
                if (board[currentRowUp][lastMove[1]].equals(opponentSymbol)) {
                    vertical++;
                } else if (board[currentRowUp][lastMove[1]].equals(" ")) {
                    verticalMoves.add(new int[]{currentRowUp, lastMove[1]});
                    if (currentRowUp > minimumIndex) {
                        if (board[currentRowUp - 1][lastMove[1]].equals(opponentSymbol)) {
                            vertical++;
                        }
                    }
                    upStillCounting = false;
                } else upStillCounting = false;
            }
            if (downStillCounting){
                if (board[currentRowDown][lastMove[1]].equals(opponentSymbol)) {
                    vertical++;
                } else if (board[currentRowDown][lastMove[1]].equals(" ")) {
                    verticalMoves.add(new int[]{currentRowDown, lastMove[1]});
                    if (currentRowDown < maximumIndex) {
                        if (board[currentRowDown + 1][lastMove[1]].equals(opponentSymbol)) {
                            vertical++;
                        }
                    }
                    downStillCounting = false;
                } else downStillCounting = false;
            }
            if (upLeftStillCounting) {
                if (board[currentRowUp][currentColumnLeft].equals(opponentSymbol)) {
                    diagonalLeft++;
                } else if (board[currentRowUp][currentColumnLeft].equals(" ")) {
                    diagonalLeftMoves.add(new int[]{currentRowUp, currentColumnLeft});
                    if (currentRowUp > minimumIndex && currentColumnLeft > minimumIndex) {
                        if (board[currentRowUp - 1][currentColumnLeft - 1].equals(opponentSymbol)) {
                            diagonalLeft++;
                        }
                    }
                    upLeftStillCounting = false;
                } else upLeftStillCounting = false;
            }
            if (downRightStillCounting) {
                if (board[currentRowDown][currentColumnRight].equals(opponentSymbol)) {
                    diagonalLeft++;
                } else if (board[currentRowDown][currentColumnRight].equals(" ")) {
                    diagonalLeftMoves.add(new int[]{currentRowDown, currentColumnRight});
                    if (currentRowDown < maximumIndex && currentColumnRight < maximumIndex) {
                        if (board[currentRowDown + 1][currentColumnRight + 1].equals(opponentSymbol)) {
                            diagonalLeft++;
                        }
                    }
                    downRightStillCounting = false;
                } else downRightStillCounting = false;
            }
            if (downLeftStillCounting) {
                if (board[currentRowDown][currentColumnLeft].equals(opponentSymbol)) {
                    diagonalRight++;
                } else if (board[currentRowDown][currentColumnLeft].equals(" ")) {
                    diagonalRightMoves.add(new int[]{currentRowDown, currentColumnLeft});
                    if (currentRowDown < maximumIndex && currentColumnLeft > minimumIndex) {
                        if (board[currentRowDown + 1][currentColumnLeft - 1].equals(opponentSymbol)) {
                            diagonalRight++;
                        }
                    }
                    downLeftStillCounting = false;
                } else downLeftStillCounting = false;
            }
            if (upRightStillCounting) {
                if (board[currentRowUp][currentColumnRight].equals(opponentSymbol)) {
                    diagonalRight++;
                } else if (board[currentRowUp][currentColumnRight].equals(" ")) {
                    diagonalRightMoves.add(new int[]{currentRowUp, currentColumnRight});
                    if (currentRowUp > minimumIndex && currentColumnRight < maximumIndex) {
                        if (board[currentRowUp - 1][currentColumnRight + 1].equals(opponentSymbol)) {
                            diagonalRight++;
                        }
                    }
                    upRightStillCounting = false;
                } else upRightStillCounting = false;
            }
        }

        List<Integer> lengths = new ArrayList<>();
        if (horizontalMoves.size() > 0) lengths.add(horizontal);
        if (verticalMoves.size() > 0) lengths.add(vertical);
        if (diagonalLeftMoves.size() > 0) lengths.add(diagonalLeft);
        if (diagonalRightMoves.size() > 0) lengths.add(diagonalRight);
        if (lengths.size() < 1){
            return makeMove(board);
        }
        Collections.sort(lengths, Collections.reverseOrder());
        System.out.println(lengths);

        if (horizontal == lengths.get(0)) possibleMoves.addAll(horizontalMoves);
        if (vertical == lengths.get(0)) possibleMoves.addAll(verticalMoves);
        if (diagonalLeft == lengths.get(0)) possibleMoves.addAll(diagonalLeftMoves);
        if (diagonalRight == lengths.get(0)) possibleMoves.addAll(diagonalRightMoves);

        Random random = new Random();
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }
}
