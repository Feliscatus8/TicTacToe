package com.tictactoe.app;

import com.tictactoe.exception.InvalidFieldCoordinatesException;
import com.tictactoe.exception.SpaceTakenException;
import com.tictactoe.interaction.UserInterface;
import com.tictactoe.modes.BoardSettings;
import com.tictactoe.opponent.ComputerPlayer;

import java.util.InputMismatchException;

public class Game {

    public int playGame(String[] players, BoardSettings board) {
        UserInterface userInterface = new UserInterface();
        ComputerPlayer computerPlayer = new ComputerPlayer();

        int movesMade = 0;
        int[] lastMove = null;
        int currentPlayer = 0;
        String[] symbols = {"X", "O"};
        boolean stillPlaying = true;
        while (stillPlaying) {
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
                    return currentPlayer;
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
        return -1;
    }
}