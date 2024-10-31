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
            jsonGenerator.writeBooleanField("ordered", orderProduct.isOrdered());
            jsonGenerator.writeBooleanField("inOrder", orderProduct.isInOrder());
            jsonGenerator.writeObjectField("product", orderProduct.getProduct().getProductDetail());
            jsonGenerator.writeObjectField("sideDishes", orderProduct.getCustomerSideDishes());
//            jsonGenerator.writeObjectField("order", orderProduct.getOrderO());
            jsonGenerator.writeObjectField("timeCreated", orderProduct.getTimeCreated());
            jsonGenerator.writeObjectField("timeUpdated", orderProduct.getTimeUpdated());
            jsonGenerator.writeObjectField("timeOrdered", orderProduct.getTimeOrdered());
            jsonGenerator.writeObjectField("timePrepared", orderProduct.getTimePrepared());
            jsonGenerator.writeNumberField("quantity", orderProduct.getQuantity());
            jsonGenerator.writeBooleanField("isMaximumAllowed", orderProduct.getIsMaximum());
//            jsonGenerator.writeBooleanField("isAddToCartAllowed", orderProduct.isAddToCartAllowed());
            jsonGenerator.writeNumberField("price", orderProduct.getItemPrice());
            jsonGenerator.writeNumberField("totalPrice", orderProduct.getTotalPrice());
            jsonGenerator.writeEndObject();
        }
    }
}
