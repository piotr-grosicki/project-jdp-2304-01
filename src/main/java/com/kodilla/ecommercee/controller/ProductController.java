package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.ProductGroupNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/shop/products")
@Tag(name = "Product Controller")
public class ProductController {

    private final ProductDbService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Get list of all products")
    @ApiResponse(responseCode = "200",
            description = "List of all products returned",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))
            }
    )
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productService.getProductList();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @Operation(summary = "Get product with specified id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Product with specified id returned",
                    content = {
                        @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse (responseCode = "404",
                    description = "Product with given id does not exist",
                    content = @Content)
    })
    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@Parameter(description = "Id of a product to return")
                                                     @PathVariable Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProductById(productId)));
    }

    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product was created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Group with given id does not exist",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws ProductGroupNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        productService.saveProduct(product);
        return ResponseEntity.ok(productMapper.mapToProductDto(product));
    }

    @Operation(summary = "Update product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product was updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Group with given id does not exist",
                    content = @Content)
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws ProductGroupNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(productMapper.mapToProductDto(savedProduct));
    }

    @Operation(summary = "Delete product with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product was deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Product with given id does not exist",
                    content = @Content)
    })
    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@Parameter(description = "Id of a product to delete")
                                                  @PathVariable Long productId) throws ProductNotFoundException {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().build();
    }
}
