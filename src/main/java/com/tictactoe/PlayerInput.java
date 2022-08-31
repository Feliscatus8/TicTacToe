package com.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerInput {
    public String getPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public int getPlayerInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
