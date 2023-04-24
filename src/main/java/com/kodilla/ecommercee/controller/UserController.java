package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
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

@RestController
@RequestMapping(value = "/v1/shop/users")
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {

    private final UserMapper userMapper;
    private final UserDbService userDbService;

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "200",
                description = "New user created",
                content = {
                        @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserDto.class))
                }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userDbService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @Operation(summary = "Block user with a given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                        description = "User was blocked",
                        content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "404",
                        description = "User not found",
                        content = @Content
            )
    })
    @PutMapping("block/{userId}")
    public ResponseEntity<UserDto> blockUser(@Parameter(description = "id of a user to block")
                                                 @PathVariable Long userId) throws UserNotFoundException {
        User user = userDbService.blockUser(userId);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @Operation(summary = "Generate authentication key for a given user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Authentication key was generated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
    })
    @PutMapping("generateKey")
    public ResponseEntity<UserDto> generateUserActivationKey(@RequestBody UserDto userDto) throws UserNotFoundException {
        User user = userDbService.generateActivationKey(userDto);
        User savedUser = userDbService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }
}