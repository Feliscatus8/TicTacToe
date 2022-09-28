package com.tictactoe.statistics;

import com.tictactoe.interaction.UserInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ResultsManager {
    private final Map<String, Integer> winsAgainstPlayers = new HashMap<>();
    private final Map<String, Integer> winsAgainstComputer = new HashMap<>();
    private final Map<String, Integer> winsAgainstAdvancedComputer = new HashMap<>();

    private final Map<String, Integer> lossesAgainstPlayers = new HashMap<>();
    private final Map<String, Integer> lossesAgainstComputer = new HashMap<>();
    private final Map<String, Integer> lossesAgainstAdvancedComputer = new HashMap<>();
    private final File savedStatistics = new File("statistics.list");

    public void addWin(String name, String opponent) {
        int wins = 0;
        switch (opponent) {
            case "computer":
                if (this.winsAgainstComputer.containsKey(name)) {
                    wins = winsAgainstComputer.get(name);
                }
                winsAgainstComputer.put(name, wins + 1);
                break;
            case "advanced computer":
                if (this.winsAgainstAdvancedComputer.containsKey(name)) {
                    wins = winsAgainstAdvancedComputer.get(name);
                }
                winsAgainstAdvancedComputer.put(name, wins + 1);
                break;
            default:
                if (this.winsAgainstPlayers.containsKey(name)) {
                    wins = winsAgainstPlayers.get(name);
                }
                winsAgainstPlayers.put(name, wins + 1);
                break;
        }
    }

    public void addLoss(String name, String opponent) {
        int loses = 0;
        switch (opponent) {
            case "computer":
                if (this.lossesAgainstComputer.containsKey(name)) {
                    loses = lossesAgainstComputer.get(name);
                }
                lossesAgainstComputer.put(name, loses + 1);
                break;
            case "advanced computer":
                if (this.lossesAgainstAdvancedComputer.containsKey(name)) {
                    loses = lossesAgainstAdvancedComputer.get(name);
                }
                lossesAgainstAdvancedComputer.put(name, loses + 1);
                break;
            default:
                if (this.lossesAgainstPlayers.containsKey(name)) {
                    loses = lossesAgainstPlayers.get(name);
                }
                lossesAgainstPlayers.put(name, loses + 1);
                break;
        }
    }

    public Map<String, Integer> getWinsAgainstPlayers() {
        return winsAgainstPlayers;
    }

    public Map<String, Integer> getWinsAgainstComputer() {
        return winsAgainstComputer;
    }

    public Map<String, Integer> getWinsAgainstAdvancedComputer() {
        return winsAgainstAdvancedComputer;
    }

    public Map<String, Integer> getLossesAgainstPlayers() {
        return lossesAgainstPlayers;
    }

    public Map<String, Integer> getLossesAgainstComputer() {
        return lossesAgainstComputer;
    }

    public Map<String, Integer> getLossesAgainstAdvancedComputer() {
        return lossesAgainstAdvancedComputer;
    }

    public void resetStatistics() {
        this.winsAgainstPlayers.clear();
        this.winsAgainstAdvancedComputer.clear();
        this.winsAgainstComputer.clear();
        this.lossesAgainstPlayers.clear();
        this.lossesAgainstAdvancedComputer.clear();
        this.lossesAgainstComputer.clear();
    }

    public void saveResults() {
        UserInterface userInterface = new UserInterface();
        Map[] rankingsArray = {winsAgainstPlayers, winsAgainstAdvancedComputer, winsAgainstComputer, lossesAgainstPlayers,
        lossesAgainstAdvancedComputer, lossesAgainstComputer};
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedStatistics));
            oos.writeObject(rankingsArray);
            oos.close();
        }catch (Exception e) {
            userInterface.displayMessage("Saving to file failed: " + e);
        }
    }

    public void loadResults() {
        UserInterface userInterface = new UserInterface();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedStatistics));
            Object readMap = ois.readObject();
            if (readMap instanceof Map[]) {
                Map<String, Integer>[] rankingsArray = new Map[6];
                System.arraycopy((Map[])readMap, 0, rankingsArray, 0, 6);
                this.winsAgainstPlayers.putAll(rankingsArray[0]);
                this.winsAgainstAdvancedComputer.putAll(rankingsArray[1]);
                this.winsAgainstComputer.putAll(rankingsArray[2]);
                this.lossesAgainstPlayers.putAll(rankingsArray[3]);
                this.lossesAgainstAdvancedComputer.putAll(rankingsArray[4]);
                this.lossesAgainstComputer.putAll(rankingsArray[5]);
            }
            ois.close();
        }catch (Exception e) {
            userInterface.displayMessage("Reading from file failed: " + e);
        }
    }
}
