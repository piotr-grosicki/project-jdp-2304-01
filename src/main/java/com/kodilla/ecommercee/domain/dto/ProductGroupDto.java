package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class ProductGroupDto {
    private long id;
    private String name;
    private List<Product> products;

    public ProductGroupDto(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
