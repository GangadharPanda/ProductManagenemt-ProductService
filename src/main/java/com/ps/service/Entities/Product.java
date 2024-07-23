package com.ps.service.Entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
   private Long id ;
   private String title ;
   private Double price;
   private String description;
   private String category;
   private String image;
}
