package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        // implementacja zwracająca sztuczne dane
        return new ProductDto(id, "Nazwa produktu", "Opis produktu", BigDecimal.valueOf(9.99), 1L);
    }

    @GetMapping("/group/{groupId}")
    public List<ProductDto> getProductsByGroupId(@PathVariable Long groupId) {
        // implementacja zwracająca sztuczne dane
        return Arrays.asList(
                new ProductDto(1L, "Nazwa produktu 1", "Opis produktu 1", BigDecimal.valueOf(9.99), groupId),
                new ProductDto(2L, "Nazwa produktu 2", "Opis produktu 2", BigDecimal.valueOf(19.99), groupId),
                new ProductDto(3L, "Nazwa produktu 3", "Opis produktu 3", BigDecimal.valueOf(29.99), groupId)
        );
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        // implementacja tworzenia produktu
        return productDto;
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        // implementacja usuwania produktu
    }
}