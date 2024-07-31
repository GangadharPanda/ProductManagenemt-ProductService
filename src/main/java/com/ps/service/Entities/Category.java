package com.ps.service.Entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends BaseClass{
    private String categoryName;
}
