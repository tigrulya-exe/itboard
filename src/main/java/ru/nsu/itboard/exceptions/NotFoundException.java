package ru.nsu.itboard.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }
}
