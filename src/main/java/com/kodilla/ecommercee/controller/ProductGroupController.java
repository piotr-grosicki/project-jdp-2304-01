package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.ProductGroupNotFoundException;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.domain.dto.ProductGroupDto;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.service.ProductGroupDbService;
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
@RequestMapping(value = "/v1/shop/groups")
@RequiredArgsConstructor
@Tag(name = "Group Controller")
public class ProductGroupController {

    private final ProductGroupDbService service;
    private final ProductGroupMapper mapper;

    @Operation(summary = "Get list of all groups")
    @ApiResponse(responseCode = "200",
            description = "List of all groups returned",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductGroupDto.class))
            }
    )
    @GetMapping
    public ResponseEntity<List<ProductGroupDto>> getGroups() {
        List<ProductGroup> foundProductGroups = service.getAllProductGroups();
        return ResponseEntity.ok(mapper.mapToProductGroupDtoList(foundProductGroups));
    }

    @Operation(summary = "Get group with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Group with specified id returned",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductGroupDto.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Group with specified does not exist",
                    content = @Content),
    })
    @GetMapping(value = "{groupId}")
    public ResponseEntity<ProductGroupDto> getGroup(@Parameter(description = "Id of a group to return")
                                                        @PathVariable Long groupId) throws ProductGroupNotFoundException {
        ProductGroup foundProductGroup = service.getProductGroup(groupId);
        return ResponseEntity.ok(mapper.mapToProductGroupDto(foundProductGroup));
    }

    @Operation(summary = "Update group")
    @ApiResponse(responseCode = "200",
            description = "Group was updated",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductGroupDto.class))
            })
    @PutMapping
    public ResponseEntity<ProductGroupDto> updateGroup(@RequestBody ProductGroupDto productGroupDto) {
        ProductGroup productGroup = mapper.mapToProductGroup(productGroupDto);
        ProductGroup savedProductGroup = service.saveProductGroup(productGroup);
        return ResponseEntity.ok(mapper.mapToProductGroupDto(savedProductGroup));
    }

    @Operation(summary = "Create group")
    @ApiResponse(responseCode = "200",
            description = "Group was created",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductGroupDto.class))
            })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody ProductGroupDto productGroupDto) {
        ProductGroup productGroup = mapper.mapToProductGroup(productGroupDto);
        service.saveProductGroup(productGroup);
        return ResponseEntity.ok().build();
    }
}
