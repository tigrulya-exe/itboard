package ru.nsu.itboard.models;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Event {

    private String id = UUID.randomUUID().toString();

    private String name;

    private String organizerId;

    private int maxParticipants;

    private String location;

    private String beginDate;

    private String duration;

    private String description;

    @Singular
    private List<UserTo> participants = new ArrayList<>();

    private boolean isEnded;

    public void generateId(){
        id = UUID.randomUUID().toString();
    }
}
