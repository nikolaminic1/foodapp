package com.example.foodapp.order.serializer.customer;

import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.List;

@JsonComponent
public class CustomerOrderSerializer {

    public static class DetailSerializer extends JsonSerializer<OrderO> {
        @Override
        public void serialize(OrderO orderO,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", orderO.getId());
            jsonGenerator.writeStringField("uuid", orderO.getUuid());
            jsonGenerator.writeObjectField("customer", orderO.getCustomer());
            jsonGenerator.writeFieldName("productList");
            jsonGenerator.writeStartArray();

            if (orderO.getProductList() != null) {
                orderO.getProductList().forEach(product -> {
                    try {
//                    jsonGenerator.writeStartObject();
                        jsonGenerator.writeObject(product);
//                    jsonGenerator.writeEndObject();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            jsonGenerator.writeEndArray();

            if (orderO.getStartTime() != null) {
                jsonGenerator.writeNumberField("startTime", orderO.getStartTime().toEpochSecond(ZoneOffset.UTC));
            }
            if (orderO.getTimeUpdated() != null) {
                jsonGenerator.writeNumberField("timeUpdated", orderO.getTimeUpdated().toEpochSecond(ZoneOffset.UTC));
            }
            if (orderO.getOrderedTime() != null) {
                jsonGenerator.writeNumberField("orderedTime", orderO.getOrderedTime().toEpochSecond(ZoneOffset.UTC));
            }
            if (orderO.getTimePrepared() != null) {
                jsonGenerator.writeNumberField("timePrepared", orderO.getTimePrepared().toEpochSecond(ZoneOffset.UTC));
            }
            if (orderO.getTimeShipped() != null) {
                jsonGenerator.writeNumberField("timeShipped", orderO.getTimeShipped().toEpochSecond(ZoneOffset.UTC));
            }
            if (orderO.getTimeDelivered() != null) {
                jsonGenerator.writeNumberField("timeDelivered", orderO.getTimeDelivered().toEpochSecond(ZoneOffset.UTC));
            }

            jsonGenerator.writeBooleanField("ordered", orderO.getOrdered());
            jsonGenerator.writeObjectField("shippingAddress", orderO.getShippingAddress());
            jsonGenerator.writeObjectField("billingAddress", orderO.getBillingAddress());
            jsonGenerator.writeObjectField("payment", orderO.getPayment());
            jsonGenerator.writeObjectField("coupon", orderO.getCoupon());
            jsonGenerator.writeBooleanField("prepared", orderO.getPrepared());
            jsonGenerator.writeBooleanField("pickedUp", orderO.getPickedUp());
            jsonGenerator.writeBooleanField("delivered", orderO.getDelivered());
            jsonGenerator.writeBooleanField("refundRequested", orderO.getRefundRequested());
            jsonGenerator.writeBooleanField("refundGranted", orderO.getRefundGranted());
            jsonGenerator.writeBooleanField("isDeliveryFree", orderO.getIsDeliveryFree());
            jsonGenerator.writeNumberField("deliveryPrice", orderO.getDeliveryPrice());
            jsonGenerator.writeNumberField("price", orderO.getPrice());
            jsonGenerator.writeObjectField("business", orderO.getBusiness());

            if (orderO.getDeliveryType() != null) {
                jsonGenerator.writeStringField("deliveryType", orderO.getDeliveryType().getDeliveryType());
            }
            jsonGenerator.writeEndObject();
        }
    }

    public static class ListSerializer extends JsonSerializer<List<OrderO>> {

        @Override
        public Class<List<OrderO>> handledType() {
            return (Class<List<OrderO>>) (Class<?>) List.class;
        }

        @Override
        public void serialize(List<OrderO> orders,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {

            jsonGenerator.writeStartArray();

            orders.forEach((order) -> {
                try {
                    jsonGenerator.writeStartObject();

                    jsonGenerator.writeEndObject();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            jsonGenerator.writeEndArray();
        }
    }
}
























