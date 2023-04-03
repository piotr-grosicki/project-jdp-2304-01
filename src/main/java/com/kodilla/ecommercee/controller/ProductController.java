package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        // Zwracanie sztucznych danych dla konkretnego produktu o podanym ID
        return new ProductDto(id, "Product " + id, "Description of product " + id, new BigDecimal("9.99"), 1L);
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        // Zwracanie sztucznych danych dla listy produktów
        List<ProductDto> products = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            products.add(new ProductDto((long) i, "Product " + i, "Description of product " + i, new BigDecimal("9.99"), 1L));
        }
        return products;
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        // Implementacja dodawania produktu do bazy danych
        // Zwracanie stworzonego produktu
        return productDto;
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        // Implementacja aktualizacji produktu w bazie danych
        // Zwracanie zaktualizowanego produktu
        return productDto;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        // Implementacja usuwania produktu z bazy danych
    }

}