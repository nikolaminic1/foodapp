package com.example.foodapp.order.serializer;

import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OrderProduct_ProductSerializer extends StdSerializer<Product> {
    public OrderProduct_ProductSerializer(){
        this(null);
    }

    public OrderProduct_ProductSerializer(Class<Product> t){
        super(t);
    }

    @Override
    public void serialize(
            Product value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", value.getId());
//        jsonGenerator.writeStringField("refCode", value.getRefCode());
//
//        jsonGenerator.writeArrayFieldStart("order_products");
//        for(OrderProduct orderProduct : value.getProductList()){
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", orderProduct.getId());
//            jsonGenerator.writeObjectField("product", orderProduct.getProduct());
//            jsonGenerator.writeStringField("value", orderProduct.getValue());
//            jsonGenerator.writeStringField("codeOfVariation", orderProduct.getCodeOfVariation());
//            jsonGenerator.writeNumberField("priceOfVariation", orderProduct.getPriceOfVariation());
//            jsonGenerator.writeNumberField("priceOfVariationDiscount", orderProduct.getPriceOfVariationDiscount());
//            jsonGenerator.writeNumberField("totalPrice", orderProduct.getTotalPrice());
//            jsonGenerator.writeBooleanField("doesAffectPrice", orderProduct.getDoesAffectPrice());
//            jsonGenerator.writeEndObject();
//        }
//        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
