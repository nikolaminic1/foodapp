package com.example.foodapp.business.serializers;

import com.example.foodapp.business.model.Business;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class BusinessSerializer extends StdSerializer<Business> {
    public BusinessSerializer(Class<Business> t) {
        super(t);
    }

    public BusinessSerializer() {
        this(null);
    }

    @Override
    public void serialize(Business business,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", business.getId());
        jsonGenerator.writeStringField("status", business.getStatus().getStatus());
        jsonGenerator.writeStringField("name", business.getName());
        jsonGenerator.writeStringField("description", business.getDescription());
        jsonGenerator.writeStringField("logo", business.getLogoImage());
        jsonGenerator.writeNumberField("delivery_price", business.getPriceOfDelivery());
        jsonGenerator.writeStringField("name", business.getName());

    }
}
