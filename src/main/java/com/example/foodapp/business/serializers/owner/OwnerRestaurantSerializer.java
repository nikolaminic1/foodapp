package com.example.foodapp.business.serializers.owner;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class OwnerRestaurantSerializer {
    public static class Detail extends JsonSerializer<Business> {
        @Override
        public void serialize(Business business, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", business.getId());
            jsonGenerator.writeStringField("name", business.getName());
            jsonGenerator.writeBooleanField("status", business.getStatus().getStatus());
            jsonGenerator.writeStringField("description", business.getDescription());
            jsonGenerator.writeStringField("backgroundImage", business.getBackgroundImage());
            jsonGenerator.writeStringField("logoImage", business.getLogoImage());
            jsonGenerator.writeNumberField("priceOfDelivery", business.getPriceOfDelivery());
            jsonGenerator.writeBooleanField("isActive", business.isActive());
            jsonGenerator.writeFieldName("tags");
            jsonGenerator.writeStartArray();
            for (var tag:business.getTags()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", tag.getId());
                jsonGenerator.writeStringField("type", tag.getTagType().toString());
                jsonGenerator.writeStringField("color", tag.getColor());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeObjectField("businessLocation", business.getBusinessLocation());
            jsonGenerator.writeNumberField("averageRating", business.getAverageRating());
            jsonGenerator.writeObjectField("workingTime", business.getTimeOpened().getWorkingTime());
            jsonGenerator.writeEndObject();
        }
    }
}
