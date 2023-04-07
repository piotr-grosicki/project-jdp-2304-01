package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ProductDto {
    private long id;
    private long productId;
    private String name;
    private String description;
    private double price;
}
