package com.example.foodapp.auth.serializers;

import com.example.foodapp.auth.user.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserAdminSerializer  {
    public static class Serializer extends JsonSerializer<User> {
        @Override
        public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", user.getId());
            jsonGenerator.writeStringField("email", user.getEmail());
            jsonGenerator.writeStringField("name", user.getFirstname());
            jsonGenerator.writeStringField("role", user.getERole().name());
            jsonGenerator.writeStringField("firstname", user.getFirstname());
            jsonGenerator.writeStringField("lastname", user.getLastname());
            if (user.getGender() != null){
                jsonGenerator.writeStringField("gender", user.getGender().name());
            }
            jsonGenerator.writeStringField("phone", user.getPhone());
            jsonGenerator.writeObjectField("addresses", user.getAddresses());
            jsonGenerator.writeEndObject();
        }
    }

}
