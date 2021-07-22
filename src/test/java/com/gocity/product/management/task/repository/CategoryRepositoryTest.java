package com.gocity.product.management.task.repository;

import com.gocity.product.management.task.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = new CategoryRepository();
    }

    @Test
    void add() {
        // GIVEN
        Category category = Category.builder().name("test").id(0).build();

        // WHEN
        categoryRepository.add(category);

        // THEN
        assertEquals(category, categoryRepository.getAll().get(0));
        assertEquals(1, categoryRepository.getAll().size());
    }

    @Test
    void addNull() {
        // GIVEN
        Category category = null;

        // THEN
        assertThrows(NullPointerException.class, () -> {
            categoryRepository.add(category);
        });
    }


    @Test
    void getAll() {
        // GIVEN
        Category category = Category.builder().name("test").build();
        Category category1 = Category.builder().name("test").build();
        Category category2 = Category.builder().name("test").build();
        Category category3= Category.builder().name("test").build();

        // WHEN
        categoryRepository.add(category);
        categoryRepository.add(category1);
        categoryRepository.add(category2);
        categoryRepository.add(category3);


        // THEN
        assertEquals(category, categoryRepository.getAll().get(0));
        assertEquals(4, categoryRepository.getAll().size());
    }
}