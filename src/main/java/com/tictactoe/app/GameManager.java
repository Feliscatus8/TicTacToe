package com.tictactoe.app;

import com.tictactoe.interaction.UserInterface;
import com.tictactoe.modes.BoardSettings;
import com.tictactoe.save.GameSave;
import com.tictactoe.save.SaveManager;
import com.tictactoe.statistics.ResultsManager;

public class GameManager {
    private String[] players = {"Player1", "Player2"};

    private BoardSettings board;
    private Game game;
    private boolean playingSave = false;

    public void playGame() {
        UserInterface userInterface = new UserInterface();
        ResultsManager resultsManager = new ResultsManager();
        resultsManager.loadResults();
        SaveManager saveManager = new SaveManager();
        GameSave savedGame = saveManager.loadGame();
        boolean firstGame = true;
        boolean playAgain = true;
        if (savedGame != null) {
            this.players = savedGame.getPlayers();
            this.board = savedGame.getBoard();
            this.game = savedGame.getGame();
            this.playingSave = true;
            savedGame = null;
        }

        while (playAgain) {
            if (!playingSave) {
                this.players = userInterface.introduction(firstGame, players);
                this.board = userInterface.chooseGameMode(firstGame, board);
                this.game = new Game();
            }
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
            } else if (result == -4) {
                savedGame = new GameSave(game, board, players);
                playAgain = false;
            }
            if (result != -4) {
                userInterface.displayStatistics(resultsManager);
                playAgain = userInterface.playAgain();
                firstGame = false;
                playingSave = false;
            }
        }
        saveManager.saveGame(savedGame);
        resultsManager.saveResults();
    }
}