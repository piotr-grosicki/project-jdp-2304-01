package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Long userId;
    private List<ProductDto> products = new ArrayList<>();

    public CartDto(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }
}
