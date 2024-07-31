package com.ps.service.Repositories;

import com.ps.service.Entities.Category;
import com.ps.service.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositories extends JpaRepository<Category, Long> {
}
