package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/shop/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserDbService userDbService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userDbService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @PutMapping("block/{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        User user = userDbService.blockUser(userId);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @PutMapping("key/{userId}")
    public ResponseEntity<UserDto> generateUserActivationKey(@PathVariable Long userId) throws UserNotFoundException {
        User user = userDbService.generateActivationKey(userId);
        User savedUser = userDbService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }
}