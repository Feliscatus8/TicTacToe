package com.tictactoe.app;

import com.tictactoe.interaction.UserInterface;
import com.tictactoe.modes.Board;
import com.tictactoe.modes.BoardSettings;
import com.tictactoe.statistics.ResultsManager;

public class GameManager {
    public void playGame() {
        UserInterface userInterface = new UserInterface();
        ResultsManager resultsManager = new ResultsManager();
        resultsManager.loadResults();
        boolean firstGame = true;
        boolean playAgain = true;
        BoardSettings board = new Board();
        String[] players = {"Player1", "Player2"};
        while (playAgain) {
            players = userInterface.introduction(firstGame, players);
            board = userInterface.chooseGameMode(firstGame, board);
            Game game = new Game();
            int result = game.playGame(players, board);
            if (result >= 0) {
                String winner = players[result];
                String loser = players[(result + 1) % 2];
                if (!(winner.equalsIgnoreCase("computer") ||
                        winner.equalsIgnoreCase("advanced computer"))) {
                    resultsManager.addWin(winner, loser.toLowerCase());
                }
                if (!(loser.equalsIgnoreCase("computer") ||
                        loser.equalsIgnoreCase("advanced computer"))) {
                    resultsManager.addLoss(loser, winner.toLowerCase());
                }
            }
            userInterface.displayStatistics(resultsManager);
            playAgain = userInterface.playAgain();
            firstGame = false;
        }
        resultsManager.saveResults();
    }
}