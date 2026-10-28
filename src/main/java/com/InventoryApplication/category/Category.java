package com.InventoryApplication.category;

import jakarta.persistence.*;



import java.util.ArrayList;
import java.util.List;

import com.InventoryApplication.category.Category;
import com.InventoryApplication.product.Product;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    protected Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

    
}