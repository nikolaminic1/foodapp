package com.example.foodapp.product.serializers.restaurant;

import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.foodapp.product.serializers.admin.AdminProductSerializer.writeData;

@JsonComponent
public class RestaurantProductSerializer {

    public static class DetailSerializer extends JsonSerializer<Product> {
        @Override
        public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            writeData(product, jsonGenerator);

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
}
