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
    public int[] getMove(String player) throws InvalidFieldCoordinatesException{
        PlayerInput playerInput = new PlayerInput();
        System.out.println(player + ", it is your turn\nIn what row do you want to place your symbol?");
        int row = playerInput.getPlayerInt();
        System.out.println(player + ", it is your turn\nIn what column do you want to place your symbol?");
        int column = playerInput.getPlayerInt();
        if (row < 1 || column < 1 || row > 3 || column > 3) throw new InvalidFieldCoordinatesException("Invalid coordinates");
        return new int[]{row - 1, column - 1};
    }
    public void displayBoard(String[][] board){
        System.out.println("    c c c\n    1 2 3\nr1 |" + board[0][0] + "|" + board[0][1] + "|" + board[0][2] + "|"+
                "\nr2 |" + board[1][0] + "|" + board[1][1] + "|" + board[1][2] + "|" +
                "\nr3 |" + board[2][0] + "|" + board[2][1] + "|" + board[2][2] + "|");
    }
    public void displayMessage(String message){
        System.out.println(message);
    }
}
