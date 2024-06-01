package com.example.foodapp.auth.serializers;

import com.example.foodapp.auth.user.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserCustomerSerializer {

    public static class Serializer extends JsonSerializer<User> {
        @Override
        public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            System.out.println("*".repeat(20));
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", user.getId());
            jsonGenerator.writeStringField("email", user.getEmail());
            jsonGenerator.writeStringField("name", user.getFirstname());
            jsonGenerator.writeEndObject();
        }
    }



}
