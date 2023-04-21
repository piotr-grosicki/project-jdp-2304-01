package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.OrderDetailsNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.OrderDetails;
import com.kodilla.ecommercee.domain.dto.OrderDetailsDto;
import com.kodilla.ecommercee.mapper.OrderDetailsMapper;
import com.kodilla.ecommercee.service.OrderDetailsDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/orders")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsDbService orderDetailsDbService;
    private final OrderDetailsMapper orderDetailsMapper;

    @GetMapping
    public ResponseEntity<List<OrderDetailsDto>> getOrdersDetailsList() {
        List<OrderDetails> orderDetailsList = orderDetailsDbService.getOrderDetailsList();
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDtoList(orderDetailsList));
    }

    @PostMapping(value = "createOrderDetails/{cartId}")
    public ResponseEntity<OrderDetailsDto> createOrderDetails(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDto(orderDetailsDbService.createOrderDetails(cartId)));
    }

    @GetMapping(value = "{orderDetailsId}")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(@PathVariable Long orderDetailsId) throws OrderDetailsNotFoundException {
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDto(orderDetailsDbService.getOrderDetails(orderDetailsId)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailsDto> updateOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto) throws UserNotFoundException, CartNotFoundException, OrderDetailsNotFoundException{
        OrderDetails orderDetails = orderDetailsMapper.mapToOrderDetails(orderDetailsDto);
        orderDetailsDbService.updateOrderDetails(orderDetails);
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDto(orderDetailsDbService.updateOrderDetails(orderDetails)));
    }

    @DeleteMapping(value = "{orderDetailsId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderDetailsId) throws OrderDetailsNotFoundException {
        orderDetailsDbService.deleteOrderDetails(orderDetailsId);
        return ResponseEntity.ok().build();
    }
}
