package com.example.foodapp._api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PaginatedResponseSerializer extends JsonSerializer<PaginatedResponse> {
    @Override
    public void serialize(PaginatedResponse paginatedResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("items");
        jsonGenerator.writeStartArray();
        for (Object item: paginatedResponse.getItems()) {
            jsonGenerator.writeObject(item);
        }
        jsonGenerator.writeEndArray();

        if (paginatedResponse.getCount() != null) {
            jsonGenerator.writeNumberField("count", paginatedResponse.getCount());
        }

        if (paginatedResponse.getPages() != null) {
            jsonGenerator.writeNumberField("pages", paginatedResponse.getPages());
        }

        if (paginatedResponse.getSize() != null) {
            jsonGenerator.writeNumberField("size", paginatedResponse.getSize());
        }

        if (paginatedResponse.getPage() != null) {
            jsonGenerator.writeNumberField("page", paginatedResponse.getPage());
        }

        jsonGenerator.writeEndObject();
    }
}
