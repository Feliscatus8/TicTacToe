package com.tictactoe.exception;

public class SpaceTakenException extends Exception {
    public SpaceTakenException(final String message){
        super(message);
    }
}
