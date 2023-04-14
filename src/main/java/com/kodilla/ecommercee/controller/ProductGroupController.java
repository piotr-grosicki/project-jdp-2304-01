package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.ProductGroupNotFoundException;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.domain.dto.ProductGroupDto;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.service.ProductGroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/groups")
@RequiredArgsConstructor
public class ProductGroupController {

    private final ProductGroupDbService service;
    private final ProductGroupMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getGroups() {
        List<ProductGroup> foundProductGroups = service.getAllProductGroups();
        return ResponseEntity.ok(mapper.mapToProductGroupDtoList(foundProductGroups));
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<ProductGroupDto> getGroup(@PathVariable Long groupId) throws ProductGroupNotFoundException {
        ProductGroup foundProductGroup = service.getProductGroup(groupId);
        return ResponseEntity.ok(mapper.mapToProductGroupDto(foundProductGroup));
    }

    @PutMapping
    public ResponseEntity<ProductGroupDto> updateGroup(@RequestBody ProductGroupDto productGroupDto) {
        ProductGroup productGroup = mapper.mapToProductGroup(productGroupDto);
        ProductGroup savedProductGroup = service.saveProductGroup(productGroup);
        return ResponseEntity.ok(mapper.mapToProductGroupDto(savedProductGroup));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody ProductGroupDto productGroupDto) {
        ProductGroup productGroup = mapper.mapToProductGroup(productGroupDto);
        service.saveProductGroup(productGroup);
        return ResponseEntity.ok().build();
    }
}
