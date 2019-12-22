package ru.nsu.itboard.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Review {
    private String id = UUID.randomUUID().toString();

    private String userId;

    private int starsCount;

    private int eventId;

    private String comment;

    public void generateId(){
        id = UUID.randomUUID().toString();
    }
}