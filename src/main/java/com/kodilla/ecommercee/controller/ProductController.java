package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/shop/products")
public class ProductController {

    @GetMapping
    public ProductDto getProducts() {
        return new ProductDto(1,2,"Products","Test - get(GET) all products",10);
    }

    @GetMapping(value = "{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return new ProductDto(productId,2,"Product","Test - get(GET) single product with id: ",10);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(productDto.getId(),2,"Product","Test - update(PUT) product",10);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(3,4,"Product_A","Test - create(POST) product",10);
    }

    @DeleteMapping(value = "{productId}")
    public ProductDto deleteProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(1,2,"Product","Test - delete(DELETE) product",10);
    }
}
