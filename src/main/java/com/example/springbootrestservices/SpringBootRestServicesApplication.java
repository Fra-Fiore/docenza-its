package com.example.springbootrestservices;

import com.example.springbootrestservices.client.RestClient;
import com.example.springbootrestservices.model.ProductDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootRestServicesApplication {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		SpringApplication.run(SpringBootRestServicesApplication.class, args);
//		new RestClient().initData(getDefaultData());
	}

//	private static List<ProductDto> getDefaultData() {
//		List<ProductDto> productList = new ArrayList<>();
//		productList.add(new ProductDto("Pane", 10));
//		productList.add(new ProductDto("Pasta", 20));
//		productList.add(new ProductDto("Formaggio", 30));
//		return productList;
//	}

}
