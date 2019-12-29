package ru.nsu.itboard.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ru.nsu.itboard.util.CustomSerializer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class User {
    private String id = UUID.randomUUID().toString();

    private String name;

    @JsonSerialize(using = CustomSerializer.class)
    private Map<String, User> subscribers = new HashMap<>();

    @JsonSerialize(using = CustomSerializer.class)
    private Map<String, User> subscriptions = new HashMap<>();

    private boolean isPrivateProfile;

    private String login;

    private String password;

    public void generateId(){
        id = UUID.randomUUID().toString();
    }
}


