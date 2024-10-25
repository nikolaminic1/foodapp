package com.example.foodapp.auth.serializers;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.product.serializers.admin.AdminProductSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.List;

@JsonComponent
public class AdminUsersSerializer  {

    private static void writeUserData(User user, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("firstname", user.getFirstname());
        jsonGenerator.writeStringField("lastname", user.getLastname());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeStringField("phone", user.getPhone());
        jsonGenerator.writeStringField("role", user.getRole());
        jsonGenerator.writeBooleanField("isEnabled", user.isEnabled());
        if (user.getGender() != null){
            jsonGenerator.writeStringField("gender", user.getGender().name());
        }
    }

    public static class ListSerializer extends JsonSerializer<List<User>> {
        @Override
        public Class<List<User>> handledType() {
            return (Class<List<User>>) (Class<?>) List.class;
        }

        @Override
        public void serialize(List<User> users, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeFieldName("users");
            jsonGenerator.writeStartArray();
            users.forEach((user -> {
                try {
                    jsonGenerator.writeStartObject();
                    writeUserData(user, jsonGenerator);
                    jsonGenerator.writeEndObject();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }));
            jsonGenerator.writeEndArray();
        }
    }

    public static class DetailSerializer extends JsonSerializer<User> {
        @Override
        public void serialize(User user,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider
        ) throws IOException {
            jsonGenerator.writeStartObject();
            writeUserData(user, jsonGenerator);
            jsonGenerator.writeEndObject();
        }


    }
}
