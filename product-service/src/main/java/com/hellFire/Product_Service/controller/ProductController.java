package com.hellFire.Product_Service.controller;


import com.hellFire.Product_Service.Dto.ProductRequest;
import com.hellFire.Product_Service.Dto.ProductResponse;
import com.hellFire.Product_Service.model.Product;
import com.hellFire.Product_Service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void  createProduct(@RequestBody ProductRequest productRequest) {
        System.out.println("Create Product");
        productService.createProduct(productRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
       return productService.getAllProducts();
    }
}
