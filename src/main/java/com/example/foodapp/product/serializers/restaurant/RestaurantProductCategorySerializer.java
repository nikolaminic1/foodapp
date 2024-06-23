package com.example.foodapp.product.serializers.restaurant;

import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.ProductCategory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonComponent
public class RestaurantProductCategorySerializer {
    private static void writeData(ProductCategory category, JsonGenerator generator) throws IOException{
        generator.writeNumberField("id", category.getId());
    }

    public static class DetailSerializer extends JsonSerializer<ProductCategory> {

        @Override
        public void serialize(ProductCategory category, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", category.getId());
            jsonGenerator.writeStringField("nameOfCategory", category.getNameOfCategory());
            jsonGenerator.writeStringField("descOfCategory", category.getDescOfCategory());
            jsonGenerator.writeBooleanField("categoryVisible", category.getCategoryVisible());
            jsonGenerator.writeBooleanField("featured", category.getFeatured());
            jsonGenerator.writeStringField("dateCreated", category.getDateCreated().format(DateTimeFormatter.ISO_DATE_TIME));
            jsonGenerator.writeStringField("dateUpdated", category.getDateUpdated().format(DateTimeFormatter.ISO_DATE_TIME));
            jsonGenerator.writeEndObject();
        }
    }

    public static class ListSerializer extends JsonSerializer<List<ProductCategory>> {

        @Override
        public Class<List<ProductCategory>> handledType() {
            return (Class<List<ProductCategory>>) (Class<?>) List.class;
        }

        @Override
        public void serialize(List<ProductCategory> categoriesList,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {

            jsonGenerator.writeStartArray();

            categoriesList.forEach((category) -> {
                try {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", category.getId());
                    jsonGenerator.writeStringField("nameOfCategory", category.getNameOfCategory());
                    jsonGenerator.writeStringField("descOfCategory", category.getDescOfCategory());
                    jsonGenerator.writeBooleanField("categoryVisible", category.getCategoryVisible());
                    jsonGenerator.writeStringField("dateCreated", category.getDateCreated().format(DateTimeFormatter.ISO_DATE_TIME));
                    jsonGenerator.writeStringField("dateUpdated", category.getDateUpdated().format(DateTimeFormatter.ISO_DATE_TIME));

                    if (category.getFeatured() != null) {
                        jsonGenerator.writeBooleanField("featured", category.getFeatured());
                    }

                    jsonGenerator.writeEndObject();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            jsonGenerator.writeEndArray();
        }
    }
}
