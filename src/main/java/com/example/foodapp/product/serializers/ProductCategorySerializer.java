package com.example.foodapp.product.serializers;

import com.example.foodapp.product.model.ProductCategory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;


public class ProductCategorySerializer extends StdSerializer<ProductCategory> {
    public ProductCategorySerializer(){
        this(null);
    }

    public ProductCategorySerializer(Class<ProductCategory> t){
        super(t);
    }

    @Override
    public void serialize(
            ProductCategory value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", value.getId());
        jsonGenerator.writeStringField("name", value.getNameOfCategory());
        jsonGenerator.writeEndObject();
    }
}
