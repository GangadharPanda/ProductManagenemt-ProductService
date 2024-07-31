package com.ps.service.Services;

import com.ps.service.Entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductService implements IProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long id) {
        return restTemplate.getForObject("https://fakestoreapi.com/products/" + id, Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        Product[] arr = restTemplate.getForObject("https://fakestoreapi.com/products/", Product[].class);
        List<Product> products = new ArrayList<>();
        for (Product product : arr) {
            products.add(product);
        }
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        System.out.println("Adding product");
        return  restTemplate.postForObject("https://fakestoreapi.com/products/", product, Product.class);
    }

    //This one is not verified , I will need to
    @Override
    public Product updateProduct(Product product) {
        System.out.println("Updating product");
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, Product.class);
        HttpMessageConverterExtractor<Product> responseExtractor = new HttpMessageConverterExtractor<>(Product.class, restTemplate.getMessageConverters());
        return restTemplate.execute("https://fakestoreapi.com/products/", HttpMethod.PUT, requestCallback, responseExtractor);
    }

    @Override
    public void deleteProduct(Long id) {
        //boolean isValid = restTemplate.getForObject("http://localhost:8090/auth/validate/test", Boolean.class);
        boolean isValid = Boolean.TRUE.equals(restTemplate.getForObject("http://AUTHSERVICE/auth/validate/test", Boolean.class));
        if (isValid) {
            System.out.println("Deleting product after Validating it from Auth Service");
        }
        //restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }
}
