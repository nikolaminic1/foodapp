package com.example.foodapp.auth.serializers;

import com.example.foodapp.auth.user.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class UserDetailSerializer extends StdSerializer<User> {
    public UserDetailSerializer() {
        this(null);
    }

    public UserDetailSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeStringField("name", user.getFirstname());
        jsonGenerator.writeStringField("role", user.getERole().name());
        jsonGenerator.writeObjectField("profile", user);
        jsonGenerator.writeEndObject();

    }
}
