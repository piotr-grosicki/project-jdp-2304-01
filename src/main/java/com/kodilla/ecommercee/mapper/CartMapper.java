package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final ProductMapper productMapper;

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                productMapper.mapToProductDtoList(cart.getProductList())
        );
    }


}
