package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/shop/users")
public class UserController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return new UserDto(userDto.getId(), "Test - create(POST) user", "Test - lastName", true, 1L);
    }
    @PutMapping(value = "{userId}")
    public UserDto updateUserStatus(@PathVariable long userId) {
        return new UserDto(userId,"Test - update(PUT) user", "Test - lastName", false, 1L);
    }
    @PutMapping(value = "{userId}")
    public void generateUserKey(@PathVariable long userId){
    }
}
