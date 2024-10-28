package com.example.foodapp.order.serializer.customer;

import com.example.foodapp.order.model.OrderProduct;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CustomerOrderProductSerializer {
    public static class DetailSerializer extends JsonSerializer<OrderProduct> {
        @Override
        public void serialize(
                OrderProduct orderProduct,
                JsonGenerator jsonGenerator,
                SerializerProvider serializerProvider
        ) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", orderProduct.getId());
            jsonGenerator.writeEndObject();
        }
    }
}
