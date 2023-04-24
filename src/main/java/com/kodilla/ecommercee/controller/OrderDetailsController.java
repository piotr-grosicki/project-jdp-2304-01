package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.OrderDetailsNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.OrderDetails;
import com.kodilla.ecommercee.domain.dto.OrderDetailsDto;
import com.kodilla.ecommercee.mapper.OrderDetailsMapper;
import com.kodilla.ecommercee.service.OrderDetailsDbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/orders")
@RequiredArgsConstructor
@Tag(name = "Order Controller")
public class OrderDetailsController {

    private final OrderDetailsDbService orderDetailsDbService;
    private final OrderDetailsMapper orderDetailsMapper;

    @Operation(summary = "Get list of all orders")
    @ApiResponse(responseCode = "200",
            description = "List of all orders returned",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDetailsDto.class))
            }
    )
    @GetMapping
    public ResponseEntity<List<OrderDetailsDto>> getOrdersDetailsList() {
        List<OrderDetails> orderDetailsList = orderDetailsDbService.getOrderDetailsList();
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDtoList(orderDetailsList));
    }

    @Operation(summary = "Create new order")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Order was created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDetailsDto.class))
                    }),
            @ApiResponse (responseCode = "404",
                    description = "Cart with given id does not exist",
                    content = @Content)
    })
    @PostMapping(value = "createOrder/{cartId}")
    public ResponseEntity<OrderDetailsDto> createOrderDetails(@Parameter(description = "Id of a cart, which is used to create new order")
                                                                  @PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDto(orderDetailsDbService.createOrderDetails(cartId)));
    }

    @Operation(summary = "Get order with specified id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Order with specified id returned",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDetailsDto.class))
                    }),
            @ApiResponse (responseCode = "404",
                    description = "Order with given id does not exist",
                    content = @Content)
    })
    @GetMapping(value = "{orderDetailsId}")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(@Parameter(description = "Id of an order to return")
                                                               @PathVariable Long orderDetailsId) throws OrderDetailsNotFoundException {
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDto(orderDetailsDbService.getOrderDetails(orderDetailsId)));
    }

    @Operation(summary = "Update specified order")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Order was updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDetailsDto.class))
                    }),
            @ApiResponse (responseCode = "404",
                    description = "Order, Cart or User with given id does not exist",
                    content = @Content)
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailsDto> updateOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto) throws UserNotFoundException, CartNotFoundException, OrderDetailsNotFoundException{
        OrderDetails orderDetails = orderDetailsMapper.mapToOrderDetails(orderDetailsDto);
        orderDetailsDbService.updateOrderDetails(orderDetails);
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDto(orderDetailsDbService.updateOrderDetails(orderDetails)));
    }

    @Operation(summary = "Delete specified order")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Order was deleted",
                    content = @Content),
            @ApiResponse (responseCode = "404",
                    description = "Order with given id does not exist",
                    content = @Content)
    })
    @DeleteMapping(value = "{orderDetailsId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderDetailsId) throws OrderDetailsNotFoundException {
        orderDetailsDbService.deleteOrderDetails(orderDetailsId);
        return ResponseEntity.ok().build();
    }
}
