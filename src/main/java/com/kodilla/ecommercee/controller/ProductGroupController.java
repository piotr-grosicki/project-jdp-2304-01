package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductGroupDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/shop/groups")
public class ProductGroupController {
    @GetMapping
    public ProductGroupDto getGroups() {
        return new ProductGroupDto(1, "Test - get(GET) all groups");
    }

    @GetMapping(value = "{groupId}")
    public ProductGroupDto getGroup(@PathVariable Long groupId) {
        return new ProductGroupDto(groupId, "Test - get(GET) single groups");
    }

    @PutMapping
    public ProductGroupDto updateGroup(@RequestBody ProductGroupDto productGroupDto) {
        return new ProductGroupDto(productGroupDto.getId(), "Test - update(PUT) group");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDto createGroup(@RequestBody ProductGroupDto productGroupDto) {
        return new ProductGroupDto(1L, "Test - create(PUT) new group");
    }
}
