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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.foodapp.product.serializers.admin.AdminProductSerializer.writeData;
import static com.example.foodapp.product.serializers.admin.AdminProductSerializer.writeSideDishCategoriesList;

@JsonComponent
public class RestaurantProductSerializer {

    public static class DetailSerializer extends JsonSerializer<Product> {
        @Override
        public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            writeData(product, jsonGenerator);
            writeSideDishCategoriesList(product, jsonGenerator);

//            jsonGenerator.writeFieldName("skills");
//            jsonGenerator.writeStartArray(); // Start the array
//            for (var skill : product.getAppendicesCategoryList()) {
//                Map<String, String> map = new HashMap<>();
//                map.put("asdl", "asdnf");
//                jsonGenerator.writeObjectField("category1", map);
//                jsonGenerator.writeString(skill.getNameOfCategory()); // Write each skill in the array
//            }
//            jsonGenerator.writeEndArray();

//            jsonGenerator.writeFieldName("all_categories");
//            jsonGenerator.writeStartArray();
//            jsonGenerator.writeStringField("asdf", "asdpf");
//            jsonGenerator.writeObjectField("category1", new HashMap<String, String>());
//            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();

        }
    }

    public static class ListSerializer extends JsonSerializer<List<Product>> {

        @Override
        public Class<List<Product>> handledType() {
            return (Class<List<Product>>) (Class<?>) List.class;
        }

        @Override
        public void serialize(List<Product> products,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {

            jsonGenerator.writeStartArray();

            products.forEach((product) -> {
                System.out.println(product);
                try {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", product.getId());
                    jsonGenerator.writeStringField("nameOfProduct", product.getNameOfProduct());
                    if (product.getProductImage() != null) {
                        jsonGenerator.writeStringField("image", product.getProductImage().getImageUrl());
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
