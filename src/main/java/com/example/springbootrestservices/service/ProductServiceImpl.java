package com.example.springbootrestservices.service;

import com.example.springbootrestservices.entity.Product;
import com.example.springbootrestservices.model.ProductDto;
import com.example.springbootrestservices.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@Service
public class ProductServiceImpl implements ProductServiceApi {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = getAllProductsFromRepo();
        System.out.println("GET ALL Service - Product List: " + productList);
        return mapProductListToProductDtoList(productList);
    }

    @Override
    public ProductDto getProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            System.out.println("GET Service - Product: " + optionalProduct.get());
            return mapProductToProductDto(optionalProduct.get());
        } else {
            return null;
        }
    }

    @Override
    public boolean replaceProduct(ProductDto newProduct) {
        Optional<Product> optionalProduct =  productRepository.findById(newProduct.getId());
        if(optionalProduct.isPresent()) {
            Product product = mapProductDtoToProduct(newProduct);
            product.setId(newProduct.getId());
            productRepository.save(product);
            System.out.println("PUT Service - Product List: " + getAllProductsFromRepo());
            return true;
        } else {
            System.out.println("PUT Service - Product List: " + getAllProductsFromRepo());
            return false;
        }
    }

    @Override
    public boolean updateProduct(Long id, int quantity) {
        Optional<Product> optionalProduct =  productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            optionalProduct.get().setQuantity(quantity);
            productRepository.save((optionalProduct.get()));
            System.out.println("PATCH Service - Product List: " + getAllProductsFromRepo());
            return true;
        } else {
            System.out.println("PATCH Service - Product List: " + getAllProductsFromRepo());
            return false;
        }
    }

    @Override
    public Long addProduct(ProductDto newProduct) {
        Product product = productRepository.save(mapProductDtoToProduct(newProduct));
        System.out.println("ADD Service - Product: " + product);
        return product.getId();
    }

    @Override
    public void addAllProducts(List<ProductDto> productDtoList) {
        productRepository.deleteAll();
        List<Product> productList = productDtoList
                .stream()
                .map(this::mapProductDtoToProduct)
                .toList();
        productRepository.saveAll(productList);
        System.out.println("ADD ALL Service - Product List: " + productList);
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        assertTrue(optionalProduct.isEmpty());
        System.out.println("DELETE Service - Product List: " + getAllProductsFromRepo());
        return true;
    }

    @Override
    public int importCsv() {
        // TODO: implementa questo metodo in modo tale da importare i dati presenti nel file '/resurces/import_data.csv' nella tabella 'products' e restituisca il numero di elementi inseriti
        String csvFile = "classpath:import_data.csv";
        String csvSplitBy = ",";

        try {
            Resource resource = resourceLoader.getResource(csvFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            // SOLUZIONE 1
//            AtomicInteger counter = new AtomicInteger();
//            reader.lines().skip(1)
//                    .map(line -> line.split(csvSplitBy))
//                    .forEach(data -> {
//                        // Creazione di un oggetto Java con i dati del file CSV
//                        // Assumendo che il file CSV abbia colonne separate da virgola
//
//                        String name = data[0];
//                        String brand = data[1];
//                        int quantity = Integer.parseInt(data[2]);
//
//                        Product product = new Product(name, brand, quantity);
//                        productRepository.save(product);
//                        counter.addAndGet(1);
//                    });
//            return counter.get();

            // SOLUZIONE 2
            List<Product> products = reader.lines()
                    .skip(1) // Salta la riga dell'intestazione se presente
                    .map(line -> line.split(csvSplitBy))
                    .map(data -> new Product(data[0], data[1], Integer.parseInt(data[2])))
                    .toList();
            return productRepository.saveAll(products).size();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Product mapProductDtoToProduct(ProductDto productDto) {
        return new Product(productDto.getName(), productDto.getBrand(), productDto.getQuantity());
    }

    private ProductDto mapProductToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getBrand(), product.getQuantity());
    }

    private List<Product> getAllProductsFromRepo() {
        return productRepository.findAll();
    }

    private List<ProductDto> mapProductListToProductDtoList(List<Product> productList) {
        return productList
                .stream()
                .map(this::mapProductToProductDto)
                .toList();
    }
}
