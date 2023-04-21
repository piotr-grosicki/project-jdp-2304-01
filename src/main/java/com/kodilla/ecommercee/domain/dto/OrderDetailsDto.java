package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
    private Long id;
    private BigDecimal totalPrice;
    private Long cartId;
    private Long userId;

    public OrderDetailsDto(BigDecimal totalPrice, Long cartId, Long userId) {
        this.totalPrice = totalPrice;
        this.cartId = cartId;
        this.userId = userId;
    }
}
