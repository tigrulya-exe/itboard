package ru.nsu.itboard.exceptions;

public class WrongArgumentException extends RuntimeException {
    public WrongArgumentException(){
        super();
    }

    public WrongArgumentException(String message){
        super(message);
    }
}