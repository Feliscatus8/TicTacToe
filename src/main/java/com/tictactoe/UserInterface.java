package com.tictactoe;


public class UserInterface {

    public String[] introduction(){
        PlayerInput playerInput = new PlayerInput();
        System.out.println("Enter the first player's name: ");
        String player1Name = playerInput.getPlayerInput();
        while (true) {
            System.out.println("Enter the second player's name: ");
            String player2Name = playerInput.getPlayerInput();
            if (!player1Name.equals(player2Name)) return new String[]{player1Name, player2Name};
            System.out.println("Name already taken");
        }
    }

    public String chooseGameMode(){
        PlayerInput playerInput = new PlayerInput();
        System.out.println("Enter 1 for standard game 3x3(default)\nOr enter 2 for extended game 10x10 ");
        return playerInput.getPlayerInput();
    }
    public String[] symbols() {
        PlayerInput playerInput = new PlayerInput();
        System.out.println("Enter the first player's symbol (default: X): ");
        String player1Symbol = playerInput.getPlayerInput().strip().substring(0, 1);
        if (player1Symbol.equals(" ")) player1Symbol = "X";

        System.out.println("Enter the second player's symbol (default: O): ");
        String player2Symbol = playerInput.getPlayerInput();
        if (!player1Symbol.equals(player2Symbol)) return new String[]{player1Symbol, player2Symbol};
        if (player1Symbol.equals("O")) return new String[]{"O", "X"};
        return new String[]{player1Symbol, "O"};
    }
    public int[] getMove(String player, String[][] board) throws InvalidFieldCoordinatesException{
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
}
