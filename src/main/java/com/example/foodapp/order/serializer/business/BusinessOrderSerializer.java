package com.example.foodapp.order.serializer.business;

import com.example.foodapp.order.model.OrderO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.List;

@JsonComponent
public class BusinessOrderSerializer {
    public static class DetailSerializer extends JsonSerializer<OrderO> {
        @Override
        public void serialize(OrderO orderO,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeEndObject();
        }
    }

    public static class ListSerializer extends JsonSerializer<List<OrderO>> {

        @Override
        public Class<List<OrderO>> handledType() {
            return (Class<List<OrderO>>) (Class<?>) List.class;
        }

        @Override
        public void serialize(List<OrderO> orders,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {

            jsonGenerator.writeStartArray();

            orders.forEach((order) -> {
                try {
                    jsonGenerator.writeStartObject();

                    jsonGenerator.writeEndObject();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            jsonGenerator.writeEndArray();
        }
    }
}
