package ru.nsu.itboard.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.nsu.itboard.models.User;

import java.io.IOException;
import java.util.Map;

public class CustomSerializer extends JsonSerializer<Map<Long, User>> {
    @Override
    public void serialize(final Map<Long, User> value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeObject(Converter.toUserTOs(value.values()));
    }
}
