package com.tictactoe;

public class SpaceTakenException extends Exception {
    public SpaceTakenException(final String message){
        super(message);
    }
}
