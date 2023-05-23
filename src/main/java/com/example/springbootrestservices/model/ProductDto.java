package com.example.springbootrestservices.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProductDto implements Comparable<ProductDto>{
    Long id;
    String name;
    int quantity;
    String brand;

    public ProductDto(String name, String brand, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.brand = brand;
    }
    // it's needed to properly deserialize a JSON object to a target entity
    @JsonCreator
    public ProductDto(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("brand") String brand, @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", brand='" + brand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, brand);
    }

    @Override
    public int compareTo(ProductDto product) {
        return this.getId().compareTo(product.getId());
    }
}