package com.example.springbootrestservices;

import com.example.springbootrestservices.client.RestClient;
import com.example.springbootrestservices.model.ProductDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringBootRestServicesApplicationTests {
    RestClient restClient = new RestClient();

    @Test
    @Order(1)
    public void getAllProducts() throws URISyntaxException, IOException, InterruptedException {
        List<ProductDto> products = restClient.getAllProducts();
        System.out.println("GET ALL Test - Product List: " + products);
    }

    @Test
    @Order(2)
    public void getProduct() throws URISyntaxException, IOException, InterruptedException {
        ProductDto product = restClient.getProduct(1L);
        System.out.println("GET Test - Product: " + product);
    }

    @Test
    @Order(3)
    public void replaceProduct() throws URISyntaxException, IOException, InterruptedException {
        ProductDto product = new ProductDto(3L, "Latte", "Parmalat", 25);
        boolean result = restClient.replaceProduct(product);
        if (result) {
            System.out.println("REPLACE Test - Product with id " + product.getId() + " replaced");
        } else {
            System.out.println("REPLACE Test - Product with id " + product.getId() + " not present");
        }
    }

    @Test
    @Order(4)
    public void updateProduct() throws URISyntaxException, IOException, InterruptedException {
        long productId = 3L;
        int newQuantity = 35;
        boolean result = restClient.updateProduct(3L, newQuantity);
        if (result) {
            System.out.println("UPDATE Test - Product with id " + productId + " updated");
        } else {
            System.out.println("UPDATE Test - Product with id " + productId + " not present");
            System.out.println("UPDATE Test - Product created");
        }
    }

    @Test
    @Order(5)
    public void addProduct() throws URISyntaxException, IOException, InterruptedException {
        ProductDto product = new ProductDto("Gelato", "Magnum",15);
        Long productId = restClient.addProduct(product);
        System.out.println("ADD Test - Product with id " + productId + " created");
    }

    @Test
    @Order(6)
    public void deleteProduct() throws URISyntaxException, IOException, InterruptedException {
        Long productId = 3L;
        boolean isProductDeleted = restClient.deleteProduct(productId);
        if (isProductDeleted) {
            System.out.println("DELETE Test - Product with id " + productId + " deleted");
        }
    }


}
