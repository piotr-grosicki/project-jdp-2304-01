package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDetailsDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/v1/shop/orders")
public class OrderDetailsController {

    @GetMapping
    public OrderDetailsDto getOrders() {
        return new OrderDetailsDto(1L, BigDecimal.ZERO);
    }

    @GetMapping(value = "{orderId}")
    public OrderDetailsDto getOrder(@PathVariable Long orderId) {
        return new OrderDetailsDto(orderId, BigDecimal.ZERO);
    }

    @DeleteMapping(value = "{orderId}")
    public OrderDetailsDto deleteOrder(@PathVariable Long orderId) {
        return new OrderDetailsDto(orderId, BigDecimal.ZERO);
    }

    @PutMapping
    public OrderDetailsDto updateOrder(@RequestBody OrderDetailsDto orderDetailsDto) {
        return new OrderDetailsDto(orderDetailsDto.getId(), orderDetailsDto.getTotalPrice());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetailsDto createOrder(@RequestBody OrderDetailsDto orderDetailsDto) {
        return new OrderDetailsDto(1L, orderDetailsDto.getTotalPrice());
    }
}
