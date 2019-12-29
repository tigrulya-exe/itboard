package ru.nsu.itboard.models;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class User {
    private String id = UUID.randomUUID().toString();

    private String name;

    private Map<String, User> subscribers = new HashMap<>();

    private Map<String, User> subscriptions = new HashMap<>();

    private boolean isPrivateProfile;

    private String login;

    private String password;

    public void generateId(){
        id = UUID.randomUUID().toString();
    }

}
