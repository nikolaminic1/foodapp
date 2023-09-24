package com.example.foodapp.product.serializers;

import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class Variation_ProductSerializer extends StdSerializer<Product> {
    public Variation_ProductSerializer() {
        this(null);
    }

    public Variation_ProductSerializer(Class<Product> t){
        super(t);
    }

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeStringField("name", product.getNameOfProduct());
        jsonGenerator.writeEndObject();
    }
}
