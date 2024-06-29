package com.example.foodapp.product.serializers.restaurant;

import com.example.foodapp._api.PaginatedResponse;
import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

@JsonComponent
public class RestaurantProductPageSerializer extends JsonSerializer<Page> {


    @Override
    public Class<Page> handledType() {
        return (Class<Page>) (Class<?>) Page.class;
    }


    @Override
    public void serialize(Page paginatedResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("items");

        System.out.println("00000000101010010----------1--");
        for (var item : paginatedResponse.getContent()) {
            System.out.println(item);
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", item);
            jsonGenerator.writeObject(item);
        }

        jsonGenerator.writeEndArray();

        if (paginatedResponse.hasNext()) {
            String nextLink = String.format("http://localhost:8070/api/v1/business/product/product_model/list?page=%s", 2);
            jsonGenerator.writeStringField("next", nextLink);
        }
//        jsonGenerator.writeStringField("next", paginatedResponse.hasNext());
//        jsonGenerator.writeStringField("previous", paginatedResponse.getPrevious());
//        jsonGenerator.writeNumberField("count", paginatedResponse.getCount());
        jsonGenerator.writeStartArray();
    }
}
