package com.ps.service.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

    //http://localhost:9090/products
    @GetMapping
    public String product() { return "Hello to the world of Product Service"; }
}
