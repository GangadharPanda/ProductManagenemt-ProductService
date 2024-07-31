package com.ps.service.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseClass{
   private String title ;
   private Double price;
   private String description;
   @ManyToOne
   private Category category;
   private String image;
}
