package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private Double price;
    private ProductGroupDto productGroupDto;
}
