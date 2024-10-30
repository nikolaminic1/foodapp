package com.example.foodapp.product.serializers.admin;

import com.example.foodapp.product.model.SideDish;
import com.example.foodapp.product.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonComponent
public class AdminProductSerializer {

//    public static void writeSideDishCategoriesList(Product product, JsonGenerator jsonGenerator) throws IOException {
//        var categories = product.getSideDishCategoryList();
//
//        jsonGenerator.writeFieldName("sideDishCategories");
//        jsonGenerator.writeStartArray();
//        for (SideDishCategory category: categories) {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", category.getId());
//            jsonGenerator.writeStringField("nameOfCategory", category.getNameOfCategory());
//
//            if (!category.getIsRequired()){
//                jsonGenerator.writeBooleanField("isRequired", false);
//            } else {
//                jsonGenerator.writeBooleanField("isRequired", true);
//            }
//
//            jsonGenerator.writeNumberField("numberOfAllowed", category.getNumberOfAllowed());
//
//            var sideDishes =     category.getSideDishList();
//
//            jsonGenerator.writeFieldName("sideDishes");
//            jsonGenerator.writeStartArray();
//            for (SideDish sideDish : sideDishes) {
//                System.out.println(sideDish);
//                jsonGenerator.writeStartObject();
//                jsonGenerator.writeNumberField("id", sideDish.getId());
//                jsonGenerator.writeStringField("nameOfSideDish", sideDish.getNameOfSideDish());
//                jsonGenerator.writeBooleanField("doesAffectPrice", sideDish.getDoesAffectPrice());
//                jsonGenerator.writeNumberField("price", sideDish.getPrice());
//                jsonGenerator.writeEndObject();
//            }
//            jsonGenerator.writeEndArray();
//            jsonGenerator.writeEndObject();
//
//        }
//
//        jsonGenerator.writeEndArray();
//
//    }

    public static void writeData(Product product, JsonGenerator jsonGenerator) throws IOException {

        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeStringField("nameOfProduct", product.getNameOfProduct());
        jsonGenerator.writeStringField("codeOfProduct", product.getCodeOfProduct());
        jsonGenerator.writeNumberField("priceOfProduct", product.getPriceOfProduct());
//        jsonGenerator.writeNumberField("discountPrice", product.getDiscountPrice());
//        jsonGenerator.writeNumberField("discountPercentage", product.getDiscountPercentage());
//        jsonGenerator.writeBooleanField("isOnDiscount", product.getIsOnDiscount());
        jsonGenerator.writeStringField("aboutProduct", product.getAboutProduct());
        jsonGenerator.writeNumberField("preparationTime", product.getPreparationTime());

        if (product.getAvailability() != null) {
            jsonGenerator.writeStringField("availability", product.getAvailability().getAvailability());
        }

        jsonGenerator.writeNumberField("weight", product.getWeight());
        jsonGenerator.writeStringField("dataCreated", product.getDataCreated().format(DateTimeFormatter.ISO_DATE_TIME));
        jsonGenerator.writeStringField("dateUpdated", product.getDateUpdated().format(DateTimeFormatter.ISO_DATE_TIME));
        jsonGenerator.writeBooleanField("productVisible", product.getProductVisible());

        if (product.getProductImage() != null) {
            jsonGenerator.writeStringField("image", product.getProductImage().getImageUrl());
        }

        // write product category
        jsonGenerator.writeObjectField("productCategory", product.getAdminProductCategoryDetail());
//                    jsonGenerator.writeFieldName("productCategory");
//                    jsonGenerator.writeStartObject();
//                    jsonGenerator.writeNumberField("id", product.getProductCategory().getId());
//                    jsonGenerator.writeEndObject();


    }

    public static class ListSerializer extends JsonSerializer<List<Product>> {

        @Override
        public Class<List<Product>> handledType() {
            return (Class<List<Product>>) (Class<?>) List.class;
        }

        @Override
        public void serialize(List<Product> productsList,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {

            jsonGenerator.writeStartArray();

            productsList.forEach((product) -> {
                try {
                    jsonGenerator.writeStartObject();
                    writeData(product, jsonGenerator);
                    jsonGenerator.writeEndObject();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            jsonGenerator.writeEndArray();
        }
    }

    public static class DetailSerializer extends JsonSerializer<Product> {
        @Override
        public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            writeData(product, jsonGenerator);

//            jsonGenerator.writeObjectField("product_description", product.getProductDescriptionList());
            jsonGenerator.writeObjectField("product_image", product.getAdminProductImageDetail());
//            jsonGenerator.writeObjectField("product_tags", product.getAdminProductTagsList());
//            jsonGenerator.writeObjectField("side_dish_category", product.getAdminAppendixCategoryList());
            jsonGenerator.writeEndObject();
        }
    }
}
