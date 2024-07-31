package com.ps.service.Repositories;

import com.ps.service.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositories extends JpaRepository<Product, Long> {
}
