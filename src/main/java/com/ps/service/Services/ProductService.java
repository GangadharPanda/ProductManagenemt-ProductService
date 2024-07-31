package com.ps.service.Services;

import com.ps.service.Entities.Category;
import com.ps.service.Entities.Product;
import com.ps.service.Repositories.CategoryRepositories;
import com.ps.service.Repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepositories productRepositories;
    @Autowired
    private CategoryRepositories categoryRepositories;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional =productRepositories.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositories.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        System.out.println("Adding product");
        product.setId(null);
        Category category = product.getCategory();
        if(category.getId() == null){
            categoryRepositories.save(category);
        }
        return productRepositories.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        System.out.println("Updating product");
        return productRepositories.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepositories.deleteById(id);
    }
}
