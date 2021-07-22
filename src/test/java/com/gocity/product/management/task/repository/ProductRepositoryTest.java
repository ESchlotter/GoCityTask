package com.gocity.product.management.task.repository;

import com.gocity.product.management.task.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void add() {
        // GIVEN
        Product product = Product.builder().name("test").build();

        // WHEN
        productRepository.add(product);

        // THEN
        assertEquals(product, productRepository.getAll().get(0));
        assertEquals(1, productRepository.getAll().size());
    }

    @Test
    void addNull() {
        // GIVEN
        Product product = null;

        // THEN
        assertThrows(NullPointerException.class, () -> {
            productRepository.add(product);
        });
    }


    @Test
    void getAll() {
        // GIVEN
        Product product = Product.builder().name("test").build();
        Product product1 = Product.builder().name("test").build();
        Product product2 = Product.builder().name("test").build();
        Product product3= Product.builder().name("test").build();

        // WHEN
        productRepository.add(product);
        productRepository.add(product1);
        productRepository.add(product2);
        productRepository.add(product3);


        // THEN
        assertEquals(product, productRepository.getAll().get(0));
        assertEquals(4, productRepository.getAll().size());
    }
}