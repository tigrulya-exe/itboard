package ru.nsu.itboard.models;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class User {
    private String id = UUID.randomUUID().toString();

    private String login;

    private String name;

    private Map<String, User> subscribers;

    private Map<String, User> subscriptions;

    private boolean isPrivateProfile;
    // lol
    private String encryptedLogin;

    private String encryptedPassword;

    public void generateId(){
        id = UUID.randomUUID().toString();
    }

}
