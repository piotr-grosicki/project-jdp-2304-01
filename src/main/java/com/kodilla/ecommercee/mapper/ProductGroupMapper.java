package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.domain.dto.ProductGroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductGroupMapper {

    public ProductGroup mapToProductGroup(final ProductGroupDto productGroupDto) {
        return new ProductGroup(
                productGroupDto.getId(),
                productGroupDto.getName(),
                productGroupDto.getProducts());
    }

    public ProductGroupDto mapToProductGroupDto(final ProductGroup productGroup) {
        return new ProductGroupDto(
                productGroup.getId(),
                productGroup.getName(),
                productGroup.getProducts());
    }

    public List<ProductGroupDto> mapToProductGroupDtoList(final List<ProductGroup> productGroupList) {
        return productGroupList.stream()
                .map(this::mapToProductGroupDto)
                .collect(Collectors.toList());
    }
}
