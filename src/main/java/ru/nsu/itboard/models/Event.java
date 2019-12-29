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

    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String name;

    private String organizerId;

    private int maxParticipants;

    private String location;

    private String beginDate;

    private String duration;

    private String description;

    public Event(){
    }

    public Event(String id, String name, String organizerId, int maxParticipants, String location, String beginDate, String duration, String description, List<UserTo> participants, boolean isEnded) {
        this.id = id;
        this.name = name;
        this.organizerId = organizerId;
        this.maxParticipants = maxParticipants;
        this.location = location;
        this.beginDate = beginDate;
        this.duration = duration;
        this.description = description;
        this.participants = participants;
        this.isEnded = isEnded;
    }

    @Builder.Default
    private List<UserTo> participants = new ArrayList<>();

    private boolean isEnded;

    public void generateId(){
        id = UUID.randomUUID().toString();
    }
}
