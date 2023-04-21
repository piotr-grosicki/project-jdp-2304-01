package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/shop/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserDbService userDbService;


    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userDbService.getAllUsers();
        List<UserDto> usersDto = users.stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") final Long id) throws UserNotFoundException {
        User user = userDbService.getUser(id).orElseThrow(UserNotFoundException::new);
        UserDto userDto = userMapper.mapToUserDto(user);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody final UserDto userDto) {
        User createdUser = userMapper.mapToUser(userDto);
        userDbService.addNewUser(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<UserDto> blockUser(@PathVariable("id") final Long id) throws UserNotFoundException {
        User user = userDbService.getUser(id).orElseThrow(UserNotFoundException::new);
        user.setStatus(false);
        UserDto userDto = userMapper.mapToUserDto(user);
        return ResponseEntity.ok().body(userDto);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User toSave = userDbService.addNewUser(user);
        UserDto updatedUserDto = userMapper.mapToUserDto(toSave);
        return ResponseEntity.ok().body(updatedUserDto);
    }

}