package com.tictactoe.interaction;


import com.tictactoe.app.GameManager;
import com.tictactoe.exception.InvalidFieldCoordinatesException;
import com.tictactoe.modes.BigBoard;
import com.tictactoe.modes.Board;
import com.tictactoe.modes.BoardSettings;
import com.tictactoe.statistics.ResultsManager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserInterface {

    public String[] introduction(boolean firstGame, String[] oldPlayerNames) {
        PlayerInput playerInput = new PlayerInput();
        if (!firstGame) {
            System.out.println("Would you like to change the players? y/n(default)");
            String changePlayers = playerInput.getPlayerInput();
            if (!changePlayers.equalsIgnoreCase("y")) return oldPlayerNames;
        }
        System.out.println("If you want to play against the computer, type \"Computer\" or \"Advanced Computer\" as your opponent's name");
        System.out.println("\nEnter the first player's name: ");
        String player1Name = playerInput.getPlayerInput();
        while (true) {
            System.out.println("Enter the second player's name: ");
            String player2Name = playerInput.getPlayerInput();
            if (!player1Name.equals(player2Name)) return new String[]{player1Name, player2Name};
            System.out.println("Name already taken");
        }
    }

    public BoardSettings chooseGameMode(boolean firstGame, BoardSettings oldBoard) {
        PlayerInput playerInput = new PlayerInput();
        if (!firstGame) {
            System.out.println("Would you like to change the game mode? y/n(default)");
            String changeMode = playerInput.getPlayerInput();
            if (!changeMode.equalsIgnoreCase("y")) {
                if (oldBoard.getClass().equals(BigBoard.class)) return new BigBoard();
                return new Board();
            }
        }
        System.out.println("Enter 1 for standard game 3x3(default)\nOr enter 2 for extended game 10x10 ");
        String mode = playerInput.getPlayerInput();
        if (mode.equals("2")) {
            return new BigBoard();
        }
        return new Board();
    }

    public boolean playAgain() {
        PlayerInput playerInput = new PlayerInput();
        System.out.println("Would you like to play again? y(default)/n");
        String reply = playerInput.getPlayerInput();
        return !reply.equalsIgnoreCase("n");
    }

    public int[] getMove(String player, String[][] board) throws InvalidFieldCoordinatesException {
        PlayerInput playerInput = new PlayerInput();
        System.out.println(player + ", it is your turn\nIn what row do you want to place your symbol?");
        int row = playerInput.getPlayerInt();
        System.out.println(player + ", it is your turn\nIn what column do you want to place your symbol?");
        int column = playerInput.getPlayerInt();
        if (row < 1 || column < 1 || row > board.length || column > board.length) throw new InvalidFieldCoordinatesException("Invalid coordinates");
        return new int[]{row - 1, column - 1};
    }

    public void displayBoard(String[][] board){
        String rowToDisplay = "     ";
        String rowToDisplay1 = "     ";
        for (int n = 1; n < board[0].length + 1; n++){
            rowToDisplay += "c ";
            rowToDisplay1 += n + " ";
        }
        System.out.println(rowToDisplay);
        System.out.println(rowToDisplay1);
        int rowNumber = 0;
        for (String[] row: board) {
            rowNumber++;
            rowToDisplay = "r";
            if (rowNumber <= 9) rowToDisplay += " ";
            rowToDisplay += rowNumber + " |";
            for (String field : row) {
                rowToDisplay += field + "|";
            }
            System.out.println(rowToDisplay);
        }
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public void displayStatistics(ResultsManager resultsManager) {
        PlayerInput playerInput = new PlayerInput();
        System.out.println("\nWould you like to see the game statistics? y/n(default)");
        String showStatistics = playerInput.getPlayerInput();
        if (showStatistics.equalsIgnoreCase("y")) {
            boolean show = true;
            while (show) {
                System.out.println("\nWhat ranking would you like to see?\n\n 1 wins against other players" +
                        "\n 2 wins against advanced computer opponent\n 3 wins against standard computer opponent" +
                        "\n 4 losses against other players\n 5 losses against advanced computer opponent" +
                        "\n 6 loses against standard computer opponent\n 7 all rankings" +
                        "\n 8 exit the game statistics");
                String choice = playerInput.getPlayerInput();
                switch (choice) {
                    case "1":
                        System.out.println("\n Wins against other players");
                        displayRanking(resultsManager.getWinsAgainstPlayers());
                        break;
                    case "2":
                        System.out.println("\n Wins against the advanced computer opponent");
                        displayRanking(resultsManager.getWinsAgainstAdvancedComputer());
                        break;
                    case "3":
                        System.out.println("\n Wins against the standard computer opponent");
                        displayRanking(resultsManager.getWinsAgainstComputer());
                        break;
                    case "4":
                        System.out.println("\n Losses against other players");
                        displayRanking(resultsManager.getLossesAgainstPlayers());
                        break;
                    case "5":
                        System.out.println("\n Losses against the advanced computer opponent");
                        displayRanking(resultsManager.getLossesAgainstAdvancedComputer());
                        break;
                    case "6":
                        System.out.println("\n Losses against the standard computer opponent");
                        displayRanking(resultsManager.getLossesAgainstComputer());
                        break;
                    case "7":
                        System.out.println("\n Wins against other players");
                        displayRanking(resultsManager.getWinsAgainstPlayers());
                        System.out.println("\n Wins against the advanced computer opponent");
                        displayRanking(resultsManager.getWinsAgainstAdvancedComputer());
                        System.out.println("\n Wins against the standard computer opponent");
                        displayRanking(resultsManager.getWinsAgainstComputer());
                        System.out.println("\n Losses against other players");
                        displayRanking(resultsManager.getLossesAgainstPlayers());
                        System.out.println("\n Losses against the advanced computer opponent");
                        displayRanking(resultsManager.getLossesAgainstAdvancedComputer());
                        System.out.println("\n Losses against the standard computer opponent");
                        displayRanking(resultsManager.getLossesAgainstComputer());
                        break;
                    case "10":
                        System.out.println("Are you certain you want to reset all statistics? y/n(default)");
                        String reset = playerInput.getPlayerInput();
                        if (reset.equalsIgnoreCase("y")) resultsManager.resetStatistics();
                        break;
                    default:
                        show = false;
                }
            }
        }
    }

    public void displayRanking(Map<String, Integer> ranking) {
        List<String> rankingList = ranking.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Entry -> (Entry.getValue() + "        " + Entry.getKey()))
                .toList();
        Collections.reverse(rankingList);
        for (String result: rankingList) {
            System.out.println(result);
        }
    }
}
