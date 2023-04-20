package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exceptions.ProductGroupNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.service.ProductDbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    private ProductDbService productDbService;

    public ProductMapper(ProductDbService productDbService) {
        this.productDbService = productDbService;
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getProductGroup().getId()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public Product mapToProduct(final ProductDto productDto) throws ProductGroupNotFoundException {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDbService.getGroup(productDto.getGroupId())
        );
    }
}
