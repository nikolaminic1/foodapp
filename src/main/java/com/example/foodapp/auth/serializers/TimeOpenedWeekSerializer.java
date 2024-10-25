package com.example.foodapp.auth.serializers;

import com.example.foodapp.auth.user.User;
import com.example.foodapp.business.model.TimeOpenedWeek;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class TimeOpenedWeekSerializer extends StdSerializer<TimeOpenedWeek> {
    public TimeOpenedWeekSerializer() {
        this(null);
    }

    public TimeOpenedWeekSerializer(Class<TimeOpenedWeek> t) {
        super(t);
    }

    public static class Serializer extends JsonSerializer<User> {
        @Override
        public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        }
    }

    @Override
    public void serialize(TimeOpenedWeek timeOpenedWeek,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart("monday");
//        jsonGenerator.writeStringField("opened", timeOpenedWeek.getTimeOpenedDayMonday().getTimeOpen());
//        jsonGenerator.writeStringField("closed", timeOpenedWeek.getTimeOpenedDayMonday().getTimeClose());
//        jsonGenerator.writeBooleanField("isNonStop", timeOpenedWeek.getTimeOpenedDayMonday().getIsNonStop());
        jsonGenerator.writeEndObject();
    }
}
