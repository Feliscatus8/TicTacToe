package com.tictactoe.save;

import com.tictactoe.interaction.UserInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    private final File savedGames = new File("saves.list");

    public void saveGame(GameSave savedGame) {
        UserInterface userInterface = new UserInterface();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedGames));
            oos.writeObject(savedGame);
            oos.close();
        }catch (Exception e) {
            userInterface.displayMessage("Saving to file failed: " + e);
        }
    }

    public GameSave loadGame() {
        UserInterface userInterface = new UserInterface();
        GameSave savedGame;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedGames));
            Object readMap = ois.readObject();
            if (readMap instanceof GameSave) {
                savedGame = (GameSave) readMap;
                return savedGame;
            }
            ois.close();
        }catch (Exception e) {
            userInterface.displayMessage("Reading from file failed: " + e);
        }
        return null;
    }
}
