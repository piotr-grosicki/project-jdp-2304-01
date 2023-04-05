package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/v1/shop/orders")
public class OrderController {

    @GetMapping
    public OrderDto getOrders() {
        return new OrderDto(1L, BigDecimal.ZERO);
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return new OrderDto(orderId, BigDecimal.ZERO);
    }

    @DeleteMapping(value = "{orderId}")
    public OrderDto deleteOrder(@PathVariable Long orderId) {
        return new OrderDto(orderId, BigDecimal.ZERO);
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return new OrderDto(orderDto.getId(), orderDto.getTotalPrice());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return new OrderDto(1L, orderDto.getTotalPrice());
    }
}
