package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/shop/products")
public class ProductController {


    @GetMapping
    public GenericEntity getProducts() {
        return new GenericEntity("Test - get(GET) all products");
    }

    @GetMapping(value = "{productId}")
    public GenericEntity getProduct(@PathVariable Long productId) {
        return new GenericEntity("Test - get(GET) single product with id: " + productId);
    }

    @PutMapping
    public GenericEntity updateProduct() {
        return new GenericEntity("Test - update(PUT) product");
    }

    @PostMapping
    public GenericEntity createProduct() {
        return new GenericEntity("Test - create(POST) product");
    }

    @DeleteMapping
    public GenericEntity deleteProduct() {
        return new GenericEntity("Test - delete(DELETE) product");
    }

}