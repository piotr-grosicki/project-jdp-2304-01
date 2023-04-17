package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.OrderDetails;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDetailsDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderDetailsMapper;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/shop/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartDbService service;
    private final CartMapper mapper;
    private final OrderDetailsMapper orderDetailsMapper;

    @GetMapping("{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable long cartId) throws CartNotFoundException{
        return ResponseEntity.ok(mapper.mapToCartDto(service.getCart(cartId)));
    }

    @PostMapping("createCart/forUser/{userId}")
    public ResponseEntity<CartDto> createCart(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(mapper.mapToCartDto(service.createCart(userId)));
    }

    @PutMapping("addToCart/{cartId}/product/{productId}")
    public ResponseEntity<CartDto> updateCart(@PathVariable Long cartId,
                                              @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(mapper.mapToCartDto(service.addToCart(cartId, productId)));
    }

    @PutMapping("createOrder/{cartId}")
    public ResponseEntity<OrderDetailsDto> createOrder(@PathVariable Long cartId) throws CartNotFoundException {
        OrderDetails orderDetails = service.createOrder(cartId);
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDto(orderDetails));
    }

    @DeleteMapping("removeFromCart/{cartId}/product/{productId}")
    public ResponseEntity<CartDto> deleteCart(@PathVariable Long cartId,
                                              @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(mapper.mapToCartDto(service.deleteFromCart(cartId, productId)));
    }
}
