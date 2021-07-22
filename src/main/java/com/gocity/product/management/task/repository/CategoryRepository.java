package com.gocity.product.management.task.repository;

import com.gocity.product.management.task.model.Category;
import com.gocity.product.management.task.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    // This would be in a DB in practice
    private List<Category> categories = new ArrayList<>();

    public void add(Category category) {
        if (category == null) {
            throw new NullPointerException();
        }
        categories.add(category);
    }

    public List<Category> getAll() {
        return categories;
    }

    public Category get(int categoryId) {
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                return category;
            }
        }
        return null;
    }
}