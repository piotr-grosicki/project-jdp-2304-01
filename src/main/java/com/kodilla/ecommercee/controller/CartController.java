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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/shop/carts")
@RequiredArgsConstructor
@Tag(name = "Cart Controller")
public class CartController {

    private final CartDbService service;
    private final CartMapper mapper;
    private final OrderDetailsMapper orderDetailsMapper;

    @Operation(summary = "Get cart with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cart with specified id returned",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartDto.class))
                    }),
            @ApiResponse (responseCode = "404",
                    description = "Cart with given id does not exist",
                    content = @Content)
    })
    @GetMapping("{cartId}")
    public ResponseEntity<CartDto> getCart(@Parameter(description = "Id of a cart to return")
                                               @PathVariable long cartId) throws CartNotFoundException{
        return ResponseEntity.ok(mapper.mapToCartDto(service.getCart(cartId)));
    }

    @Operation(summary = "Create new empty cart for a specified user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cart was created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartDto.class))
                    }),
            @ApiResponse (responseCode = "404",
                    description = "User with given id does not exist",
                    content = @Content)
    })
    @PostMapping("createCart/forUser/{userId}")
    public ResponseEntity<CartDto> createCart(@Parameter(description = "Id of an user, for which to create new cart")
                                                  @PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(mapper.mapToCartDto(service.createCart(userId)));
    }

    @Operation(summary = "Add a specified product to a specified cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product was added to a cart",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartDto.class))
                    }),
            @ApiResponse (responseCode = "404",
                    description = "Cart or Product with given id does not exist",
                    content = @Content)
    })
    @PutMapping("addToCart/{cartId}/product/{productId}")
    public ResponseEntity<CartDto> updateCart(@Parameter(description = "Id of a cart to which add a product")
                                                    @PathVariable Long cartId,
                                              @Parameter(description = "Id of a product to add to a cart")
                                                    @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(mapper.mapToCartDto(service.addToCart(cartId, productId)));
    }

    @Operation(summary = "Create new order from an existing cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order was created successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDetailsDto.class))
                    }),
            @ApiResponse (responseCode = "404",
                    description = "Cart with given id does not exist",
                    content = @Content)
    })
    @PutMapping("createOrder/{cartId}")
    public ResponseEntity<OrderDetailsDto> createOrder(@Parameter(description = "Id of a cart on which new order will be based")
                                                           @PathVariable Long cartId) throws CartNotFoundException {
        OrderDetails orderDetails = service.createOrder(cartId);
        return ResponseEntity.ok(orderDetailsMapper.mapToOrderDetailsDto(orderDetails));
    }

    @Operation(summary = "Delete a specified product from a specified cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product was deleted from a cart",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartDto.class))
                    }),
            @ApiResponse (responseCode = "404",
                    description = "Cart or Product with given id does not exist",
                    content = @Content)
    })
    @DeleteMapping("removeFromCart/{cartId}/product/{productId}")
    public ResponseEntity<CartDto> deleteCart(@Parameter(description = "Id of a cart from which to delete a product")
                                                    @PathVariable Long cartId,
                                              @Parameter(description = "Id of a product to delete from a cart")
                                                    @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(mapper.mapToCartDto(service.deleteFromCart(cartId, productId)));
    }
}
