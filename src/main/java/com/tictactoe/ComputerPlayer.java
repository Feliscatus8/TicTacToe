package com.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    public int[] makeMove(String[][] board){
        List<int[]> fieldsList = new ArrayList<>();
        for (int row = 0; row < board.length; row ++){
            for (int column = 0; column < board[0].length; column ++){
                fieldsList.add(new int[]{row, column});
            }
        }
        List<int[]> possibleMoves = new ArrayList<>();
        for(int[] field: fieldsList){
            if(board[field[0]][field[1]].equals(" ") ) possibleMoves.add(field);
        }
        Random random = new Random();
        int possibleMovesSize = possibleMoves.size();
        System.out.println(possibleMovesSize);
        int[] move = possibleMoves.get(random.nextInt(possibleMovesSize));
        return move;
    }
}
