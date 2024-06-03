package com.example.foodapp.auth.serializers;

import com.example.foodapp.auth.user.User;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@JsonComponent
public class UserCustomerSerializer {

    public static class Serializer extends JsonSerializer<User> {
        @Override
        public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//            System.out.println("*".repeat(20));
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", user.getId());
//            jsonGenerator.writeStringField("email", user.getEmail());
//            jsonGenerator.writeStringField("name", user.getFirstname());
//            jsonGenerator.writeStringField("role", user.getERole().name());
//            jsonGenerator.writeStringField("firstname", user.getFirstname());
//            jsonGenerator.writeStringField("lastname", user.getLastname());
//            jsonGenerator.writeStringField("phone", user.getPhone());
//
////            HashMap<String, String> obj = new HashMap<String, String>();
////            obj.put("asdf", "asdfas");
//
////            jsonGenerator.writeFieldName("addresses");
////            jsonGenerator.writeStartObject();
//            jsonGenerator.writeObjectField("addresses", user.getAddresses());
////            jsonGenerator.writeEndObject();
//
////            for (int i = 0; i < 5; i++) {
////                String m = String.format("%s", i);
////                jsonGenerator.writeFieldName(m);
////                jsonGenerator.writeStartObject();
////                jsonGenerator.writeStringField("ts", "123");
////                jsonGenerator.writeStringField("v", "123");
////                jsonGenerator.writeStringField("q", "123");
////                jsonGenerator.writeObjectField("ksfbas", obj);
////                jsonGenerator.writeEndObject();
////            }
//            jsonGenerator.writeEndObject();
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

    public static class Deserializer extends JsonDeserializer<User> {
        @Override
        public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            return null;
        }
    }



}
