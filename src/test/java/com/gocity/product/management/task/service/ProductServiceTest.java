package com.gocity.product.management.task.service;

import com.gocity.product.management.task.model.Product;
import com.gocity.product.management.task.repository.CategoryRepository;
import com.gocity.product.management.task.repository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void init() throws IOException {
        assertNotNull(productService);
    }

    @Test
    void getCategoryName() {
        String categoryName = productService.getCategoryName(1);
        assertEquals("Kitchen", categoryName);
    }

    @Test
    void getWrongCategoryName() {
        assertThrows(NullPointerException.class, () -> {
            productService.getCategoryName(0);
        });
    }

    @Test
    public void getProducts() {
        //GIVEN
        List<Product> products = productService.getProducts();
        assertNotNull(products);

        // THEN
        assertEquals(34, products.size());
    }

    @Test
    public void addProduct() {
        // GIVEN
       Product product = Product.builder().name("test").build();

       // WHEN
       productService.addProduct(product);
       List<Product> products = productService.getProducts();

       // THEN
        assertEquals(product, products.get(products.size() - 1));
    }

    @Test
    public void addNullProduct() {
        // GIVEN
        Product product = null;

        // THEN
        assertThrows(NullPointerException.class, () -> {
            productService.addProduct(product);
        });
    }
}
