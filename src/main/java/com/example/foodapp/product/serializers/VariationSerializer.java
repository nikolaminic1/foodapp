package com.example.foodapp.product.serializers;

import com.example.foodapp.product.model.Variation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class VariationSerializer extends StdSerializer<Variation> {
    public VariationSerializer() {
        this(null);
    }

    public VariationSerializer(Class<Variation> t){
        super(t);
    }

    @Override
    public void serialize(Variation variation, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", variation.getId());
        jsonGenerator.writeStringField("name", variation.getName());
//        jsonGenerator.writeArrayFieldStart("product_variations");
        log.error("error serializer");

//        for(ProductVariation productVariation : variation.getProductVariationList()){
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", productVariation.getId());
//            jsonGenerator.writeStringField("name", productVariation.getName());
//            jsonGenerator.writeStringField("value", productVariation.getValue());
//            jsonGenerator.writeStringField("codeOfVariation", productVariation.getCodeOfVariation());
//            jsonGenerator.writeNumberField("priceOfVariation", productVariation.getPriceOfVariation());
//            jsonGenerator.writeNumberField("priceOfVariationDiscount", productVariation.getPriceOfVariationDiscount());
//            jsonGenerator.writeNumberField("totalPrice", productVariation.getTotalPrice());
//            jsonGenerator.writeBooleanField("doesAffectPrice", productVariation.getDoesAffectPrice());
////            jsonGenerator.writeBooleanField("isOnDiscount", productVariation.getIsOnDiscount());
//            jsonGenerator.writeEndObject();
//        }
//        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
