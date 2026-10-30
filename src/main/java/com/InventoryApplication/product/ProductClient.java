package com.InventoryApplication.product;

import org.springframework.stereotype.Component;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import tools.jackson.databind.ObjectMapper;

@Component
public class ProductClient {

    public String getProductName(Long id) throws Exception {
        String body = Unirest
                .get("http://localhost:8080/product/" + id)
                .asString()
                .getBody();

        return new ObjectMapper().readTree(body).get("name").asText();
    }

    
}
