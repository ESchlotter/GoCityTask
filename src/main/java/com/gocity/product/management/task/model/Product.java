package com.gocity.product.management.task.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@JsonDeserialize(builder = Product.ProductBuilder.class)
public class Product {
    private String name;
    private String description;
    private String category;
    private String creationDate;
    private String updateDate;
    private String lastPurchasedDate;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getLastPurchasedDate() {
        return lastPurchasedDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", lastPurchasedDate=" + lastPurchasedDate +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "") // To be used by Jackson but still didn't seem to work
    public static class ProductBuilder {

    }
}
