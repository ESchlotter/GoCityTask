package com.gocity.product.management.task.model;

import lombok.Builder;

@Builder
public class Category {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
