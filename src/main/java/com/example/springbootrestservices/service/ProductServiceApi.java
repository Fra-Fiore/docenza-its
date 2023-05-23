package com.example.springbootrestservices.service;

import com.example.springbootrestservices.model.ProductDto;

import java.util.List;

public interface ProductServiceApi {

    List<ProductDto> getAllProducts();

    ProductDto getProduct(long id);

    boolean replaceProduct(ProductDto product);

    boolean updateProduct(Long id, int quantity);

    Long addProduct(ProductDto product);

    void addAllProducts(List<ProductDto> productDtoList);

    boolean deleteProduct(Long id);

    int importCsv();
}