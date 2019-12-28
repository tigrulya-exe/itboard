package ru.nsu.itboard.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nsu.itboard.exceptions.NotFoundException;
import ru.nsu.itboard.exceptions.WrongArgumentException;

@RestControllerAdvice("ru.nsu.itboard")
public class ItBoardExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException nfe){
        return ResponseEntity.status(404).body(nfe.getLocalizedMessage());
    }

    @ExceptionHandler(WrongArgumentException.class)
    public ResponseEntity<?> handleWrongArgument(WrongArgumentException wae){
        return ResponseEntity.status(400).body(wae.getLocalizedMessage());
    }
}
