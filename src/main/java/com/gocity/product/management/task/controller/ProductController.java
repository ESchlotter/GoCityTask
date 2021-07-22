package com.gocity.product.management.task.controller;

import com.gocity.product.management.task.model.Product;
import com.gocity.product.management.task.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @RequestMapping(produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList = productService.getProducts();

        return ResponseEntity.ok(productList);
    }

    @GetMapping
    @RequestMapping(value = "/add",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity addProduct(Product product) {
        productService.addProduct(product);
        return ResponseEntity.accepted().build();
    }
}
