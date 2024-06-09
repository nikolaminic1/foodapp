package com.example.foodapp.business.serializers;

import com.example.foodapp.business.model.Business;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.List;

@JsonComponent
public class BusinessListSerializer {

    public static class Serializer extends JsonSerializer<List<Business>> {

        @Override
        public Class<List<Business>> handledType() {
            return (Class<List<Business>>) (Class<?>) List.class;
        }

        @Override
        public void serialize(List<Business> businessList,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {

            jsonGenerator.writeStartArray();

            businessList.forEach((business -> {
                try {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", business.getId());

                    if (business.getStatus() != null){
                        jsonGenerator.writeStringField("status", business.getStatus().getStatus());
                    }

                    jsonGenerator.writeStringField("name", business.getName());
                    jsonGenerator.writeStringField("description", business.getDescription());
                    jsonGenerator.writeStringField("logo", business.getLogoImage());
                    jsonGenerator.writeNumberField("delivery_price", business.getPriceOfDelivery());
                    jsonGenerator.writeStringField("name", business.getName());
                    jsonGenerator.writeEndObject();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }));
//
//            jsonGenerator.writeEndArray();

//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", 1);
//            jsonGenerator.writeEndObject();
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", business.getId());
//            jsonGenerator.writeStringField("status", business.getStatus().getStatus());
//            jsonGenerator.writeStringField("name", business.getName());
//            jsonGenerator.writeStringField("description", business.getDescription());
//            jsonGenerator.writeStringField("logo", business.getLogoImage());
//            jsonGenerator.writeNumberField("delivery_price", business.getPriceOfDelivery());
//            jsonGenerator.writeStringField("name", business.getName());
//            jsonGenerator.writeEndObject();
//        jsonGenerator.writeObjectField("time_opened", business.getWorkingTime());

        }}


}
