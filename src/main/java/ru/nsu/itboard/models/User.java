package ru.nsu.itboard.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ru.nsu.itboard.util.Converter;

import java.io.IOException;
import java.util.Collection;
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

class CustomSerializer extends JsonSerializer<Map<Long, User>> {
    @Override
    public void serialize(final Map<Long, User> value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeObject(Converter.toUserTOs(value.values()));
    }
}
