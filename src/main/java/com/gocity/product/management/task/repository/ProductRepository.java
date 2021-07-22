package com.gocity.product.management.task.repository;

import com.gocity.product.management.task.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    // This would be in a DB in practice
    private List<Product> products = new ArrayList<>();

    public void add(Product product) {
        if (product == null) {
            throw new NullPointerException();
        }
        products.add(product);
    }

    public List<Product> getAll() {
        return products;
    }
}
