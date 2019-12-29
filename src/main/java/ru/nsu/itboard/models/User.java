package ru.nsu.itboard.models;

import lombok.Data;
import lombok.Singular;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class User {
    private String id = UUID.randomUUID().toString();

    private String name;

    @Singular
    private Map<String, User> subscribers;

    @Singular
    private Map<String, User> subscriptions;

    private boolean isPrivateProfile;

    private String login;

    private String password;

    public void generateId(){
        id = UUID.randomUUID().toString();
    }

}
