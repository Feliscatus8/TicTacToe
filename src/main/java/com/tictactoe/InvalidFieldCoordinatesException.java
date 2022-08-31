package com.tictactoe;

public class InvalidFieldCoordinatesException extends Exception {
    public InvalidFieldCoordinatesException(String message){
        super(message);
    }
}
