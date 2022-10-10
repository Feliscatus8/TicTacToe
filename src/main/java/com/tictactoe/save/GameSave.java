package com.tictactoe.save;

import com.tictactoe.app.Game;
import com.tictactoe.modes.BoardSettings;

import java.io.Serializable;

public class GameSave implements Serializable {
    private final Game game;
    private final BoardSettings board;
    private final String[] players;

    public GameSave(Game game, BoardSettings board, String[] players) {
        this.game = game;
        this.board = board;
        this.players = players;
    }

    public Game getGame() {
        return game;
    }

    public BoardSettings getBoard() {
        return board;
    }

    public String[] getPlayers() {
        return players;
    }
}
