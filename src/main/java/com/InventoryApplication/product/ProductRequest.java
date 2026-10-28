package com.InventoryApplication.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductRequest {

    @NotBlank(message = "Product name is required")
    public String name;

    @Min(value = 0, message = "Quantity cannot be negative")
    public int quantity;

    public String desc;

    @Min(value = 0, message = "Price cannot be negative")
    public double price;

    @NotBlank(message = "Category is required")
    public String category;   // only the name, like "Fast Food"
}