package com.tictactoe;

import java.util.InputMismatchException;

public class Game {

    public void playGame(){
        Board board = new Board();
        UserInterface userInterface = new UserInterface();
        String[] players = userInterface.introduction();
        int movesMade = 0;
        int currentPlayer = 0;
        String[] symbols = {"X", "O"};
        boolean stillPlaying = true;
        while (stillPlaying){
            try {
                userInterface.displayBoard(board.getBoard());
                int[] move = userInterface.getMove(players[currentPlayer]);
                board.addMove(move[0], move[1], symbols[currentPlayer]);
                userInterface.displayBoard(board.getBoard());
                movesMade ++;
                if (board.victory(move[0], move[1], symbols[currentPlayer])) {
                    userInterface.displayMessage(players[currentPlayer] + " won the game!");
                    stillPlaying = false;
                }
                if (movesMade == 9) {
                    userInterface.displayMessage("The game was a draw");
                    stillPlaying = false;
                }
                currentPlayer = (currentPlayer + 1) % 2;
            }catch (InputMismatchException e){
                userInterface.displayMessage("row and column must be mumbers " + e);
            }catch (InvalidFieldCoordinatesException e){
                userInterface.displayMessage(e + "\nRow and column number must be 1, 2 or 3");
            }catch (SpaceTakenException e){
                userInterface.displayMessage("Unable to make the move: " + e);

            }
        }
    }

}