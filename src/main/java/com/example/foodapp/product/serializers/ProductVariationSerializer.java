package com.example.foodapp.product.serializers;

import com.example.foodapp.product.model.ProductVariation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ProductVariationSerializer extends StdSerializer<ProductVariation> {
    public ProductVariationSerializer() {
        this(null);
    }

    public ProductVariationSerializer(Class<ProductVariation> t){
        super(t);
    }

    @Override
    public void serialize(ProductVariation productVariation, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException{
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", productVariation.getId());
        jsonGenerator.writeStringField("name", productVariation.getName());
        jsonGenerator.writeStringField("value", productVariation.getValue());
        jsonGenerator.writeStringField("codeOfVariation", productVariation.getCodeOfVariation());
        jsonGenerator.writeNumberField("priceOfVariation", productVariation.getPriceOfVariation());
        jsonGenerator.writeNumberField("priceOfVariationDiscount", productVariation.getPriceOfVariationDiscount());
        jsonGenerator.writeNumberField("totalPrice", productVariation.getTotalPrice());
        jsonGenerator.writeBooleanField("doesAffectPrice", productVariation.getDoesAffectPrice());
        jsonGenerator.writeBooleanField("isOnDiscount", productVariation.getIsOnDiscount());
//        jsonGenerator.writeStringField("productImage", productVariation.getValue());

        jsonGenerator.writeEndObject();
    }
}
