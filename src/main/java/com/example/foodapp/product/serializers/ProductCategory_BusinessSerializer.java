package com.example.foodapp.product.serializers;

import com.example.foodapp.business.model.Business;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ProductCategory_BusinessSerializer extends StdSerializer<Business> {
    public ProductCategory_BusinessSerializer(){
        this(null);
    }

    public ProductCategory_BusinessSerializer(Class<Business> t){
        super(t);
    }

    @Override
    public void serialize(
            Business value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", value.getId());
        jsonGenerator.writeStringField("name", value.getName());
        jsonGenerator.writeEndObject();
    }
}
