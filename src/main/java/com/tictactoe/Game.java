package com.tictactoe;

import java.util.InputMismatchException;

public class Game {

    public void playGame(){
        UserInterface userInterface = new UserInterface();
        ComputerPlayer computerPlayer = new ComputerPlayer();
        String[] players = userInterface.introduction();
        String mode = userInterface.chooseGameMode();
        BoardSettings board;
        if (mode.equals("2")){
            board = new BigBoard();
        }else {
            board = new Board();
        }
        int movesMade = 0;
        int[] lastMove = null;
        int currentPlayer = 0;
        String[] symbols = {"X", "O"};
        boolean stillPlaying = true;
        while (stillPlaying){
            try {
                userInterface.displayBoard(board.getBoard());
                int[] move;
                if (players[currentPlayer].equalsIgnoreCase("Computer")) {
                    move = computerPlayer.makeMove(board.getBoard());
                } else if (players[currentPlayer].equalsIgnoreCase("advanced computer")) {
                    move = computerPlayer.makeMove(board.getBoard(), lastMove, symbols[(currentPlayer + 1)%2]);
            }else {
                    move = userInterface.getMove(players[currentPlayer], board.getBoard());
                }
                board.addMove(move[0], move[1], symbols[currentPlayer]);
                userInterface.displayBoard(board.getBoard());
                movesMade ++;
                lastMove = move;
                if (board.victory(move[0], move[1], symbols[currentPlayer])) {
                    userInterface.displayMessage(players[currentPlayer] + " won the game!");
                    stillPlaying = false;
                }
                if (movesMade == board.getMaxMoves()) {
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