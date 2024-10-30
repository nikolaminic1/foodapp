package com.example.foodapp.business.serializers.customer;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonComponent
public class CustomerRestaurantSerializer {
    public static void writeData(JsonGenerator jsonGenerator, Business business) throws IOException {
        jsonGenerator.writeNumberField("id", business.getId());
        jsonGenerator.writeStringField("name", business.getName());

        if (business.getStatus() != null) {
            jsonGenerator.writeBooleanField("status", business.getStatus().getStatus());
        } else {
            jsonGenerator.writeBooleanField("status", false);
        }

        jsonGenerator.writeStringField("description", business.getDescription());
        jsonGenerator.writeStringField("backgroundImage", business.getBackgroundImage());
        jsonGenerator.writeStringField("logoImage", business.getLogoImage());
        jsonGenerator.writeNumberField("priceOfDelivery", business.getPriceOfDelivery());
        jsonGenerator.writeNumberField("averageRating", business.getAverageRating());
        jsonGenerator.writeObjectField("workingTime", business.getTimeOpened().getWorkingTime());
        jsonGenerator.writeObjectField("tags", business.getTags());
        if (business.getBusinessLocation() != null) {
            jsonGenerator.writeObjectField("businessLocation", business.getBusinessLocation().getLocationDetail());
        }
    }

    public static void writeProductCategories(JsonGenerator jsonGenerator, Business business) throws IOException {
        jsonGenerator.writeFieldName("productCategories");
        jsonGenerator.writeStartArray();
        business.getProductCategories().forEach((category) -> {
            if (category.getCategoryVisible()) {
                try {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("id", category.getId());
                    jsonGenerator.writeStringField("nameOfCategory", category.getNameOfCategory());
                    jsonGenerator.writeStringField("descOfCategory", category.getDescOfCategory());
                    jsonGenerator.writeBooleanField("featured", category.getFeatured());
                    jsonGenerator.writeStringField("dateCreated", category.getDateCreated()
                            .format(DateTimeFormatter.ISO_DATE_TIME));
                    jsonGenerator.writeStringField("dateUpdated", category.getDateUpdated()
                            .format(DateTimeFormatter.ISO_DATE_TIME));

                    jsonGenerator.writeFieldName("products");
                    jsonGenerator.writeStartArray();
                    for (Product product : category.getProductList()) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeNumberField("id", product.getId());
                        jsonGenerator.writeStringField("nameOfProduct", product.getNameOfProduct());
                        jsonGenerator.writeStringField("codeOfProduct", product.getCodeOfProduct());
                        jsonGenerator.writeNumberField("priceOfProduct", product.getPriceOfProduct());
//                        jsonGenerator.writeNumberField("discountPrice", product.getDiscountPrice());
//                        jsonGenerator.writeNumberField("discountPercentage", product.getDiscountPercentage());
//                        jsonGenerator.writeBooleanField("isOnDiscount", product.getIsOnDiscount());
                        jsonGenerator.writeStringField("aboutProduct", product.getAboutProduct());
                        jsonGenerator.writeNumberField("preparationTime", product.getPreparationTime());
                        jsonGenerator.writeNumberField("weight", product.getWeight());
                        if (product.getProductImage() != null) {
                            jsonGenerator.writeStringField("image", product.getProductImage().getImageUrl());
                        }
//                        jsonGenerator.writeFieldName("sideDishCategories");
//                        jsonGenerator.writeStartArray();
//                        for (var k : product.getSideDishCategoryList()) {
//                            jsonGenerator.writeStartObject();
//                            jsonGenerator.writeNumberField("id", k.getId());
//                            jsonGenerator.writeNumberField("numberOfAllowed", k.getNumberOfAllowed());
//                            jsonGenerator.writeBooleanField("isRequired", k.getIsRequired());
//                            jsonGenerator.writeStringField("nameOfCategory", k.getNameOfCategory());
//                            jsonGenerator.writeFieldName("sideDishes");
//                            jsonGenerator.writeStartArray();
//                            for (var m : k.getSideDishList()) {
//                                jsonGenerator.writeStartObject();
//                                jsonGenerator.writeNumberField("id", m.getId());
//                                jsonGenerator.writeBooleanField("doesAffectPrice", m.getDoesAffectPrice());
//                                jsonGenerator.writeStringField("nameOfSideDish", m.getNameOfSideDish());
//                                jsonGenerator.writeNumberField("price", m.getPrice());
//                                jsonGenerator.writeEndObject();
//                            }
//                            jsonGenerator.writeEndArray();
//                            jsonGenerator.writeEndObject();
//                        }
//                        jsonGenerator.writeEndArray();
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeEndArray();
                    jsonGenerator.writeEndObject();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        jsonGenerator.writeEndArray();
    }

    public static class LessDetailSerializer extends JsonSerializer<Business> {
        @Override
        public void serialize(Business business, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            writeData(jsonGenerator, business);
            jsonGenerator.writeEndObject();
        }
    }


    public static class DetailSerializer extends JsonSerializer<Business> {
        @Override
        public void serialize(Business business, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            writeData(jsonGenerator, business);
            writeProductCategories(jsonGenerator, business);
            jsonGenerator.writeEndObject();
        }
    }

    public static class ListSerializer extends JsonSerializer<List<Business>> {
        @Override
        public Class<List<Business>> handledType() {
            return (Class<List<Business>>) (Class<?>) List.class;
        }

        @Override
        public void serialize(
                List<Business> businessList,
                JsonGenerator jsonGenerator,
                SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartArray();
            businessList.forEach((business) -> {
                try {
                    jsonGenerator.writeStartObject();
                    writeData(jsonGenerator, business);
                    jsonGenerator.writeEndObject();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            });
            jsonGenerator.writeEndArray();
        }
    }
}
