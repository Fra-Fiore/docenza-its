package com.example.springbootrestservices.controller;

import com.example.springbootrestservices.model.ProductDto;
import com.example.springbootrestservices.service.ProductServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restexample")
public class ProductController {
    @Autowired
    ProductServiceApi productServiceApi;

    @GetMapping("${products.base.uri}")
    public List<ProductDto> getAllProducts() {
        return productServiceApi.getAllProducts();
    }

    @GetMapping("${products.uri.id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productServiceApi.getProduct(id);
    }

    @PutMapping("${products.base.uri}")
    public boolean replaceProduct(@RequestBody ProductDto product) {
        return productServiceApi.replaceProduct(product);
    }

    @PatchMapping("${products.uri.update}")
    public boolean updateProduct(@PathVariable Long id, @PathVariable int quantity) {
        return productServiceApi.updateProduct(id, quantity);
    }

    @PostMapping("${products.base.uri}")
    public Long addProduct(@RequestBody ProductDto product) {
        return productServiceApi.addProduct(product);
    }

    @PostMapping("${products.uri.add.all}")
    public void addAllProducts(@RequestBody List<ProductDto> productDtoList) {
        productServiceApi.addAllProducts(productDtoList);
    }

    @DeleteMapping("${products.uri.id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return productServiceApi.deleteProduct(id);
    }

    @PostMapping("${products.base.uri}/import_csv")
    public int importCsv() {
        return productServiceApi.importCsv();
    }
}
