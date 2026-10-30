package com.InventoryApplication.category;

import java.util.List;

import org.springframework.stereotype.Component;

import com.InventoryApplication.product.ProductResponseDto;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;

@Component
public class CategoryClient {
    
    public List<String> getProductsInCategory() throws Exception {
    
        List<String> products = Unirest.get("http://localhost:8080/allProd")
                .asString()
                .getBody();
       // 2. Convert the text into a JSON array
        JsonNode array = new ObjectMapper().readTree(body);

        // 3. Loop and take only the name of each product
        List<String> names = new ArrayList<>();
        for (JsonNode product : array) {
            names.add(product.get("name").asText());
        }

        return names;
    }
    
}
