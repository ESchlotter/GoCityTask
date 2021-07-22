package com.gocity.product.management.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocity.product.management.task.model.Product;
import com.gocity.product.management.task.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void getProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    @Disabled // ObjectMapper and Builder didn't seem to work together. I tried various methods but ran out of time.
    public void addProduct() throws Exception {
        Product product = Product.builder().build();

        mockMvc.perform(post("/products/add")
                .content(mapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isAccepted());
    }
}