package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public List<UserDto> getAllUsers(){
        List<User> users = userDbService.getAllUsers();
        return users.stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public UserDto getUser(@PathVariable("id") final Long id) throws UserNotFoundException {
        User user = userDbService.getUser(id).orElseThrow(UserNotFoundException::new);
        return userMapper.mapToUserDto(user);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody final UserDto userDto){
        User createdUser = userMapper.mapToUser(userDto);
        userDbService.addNewUser(createdUser);
    }

    @PutMapping("/status/{id}")
    public UserDto blockUser(@PathVariable("id") final Long id) throws UserNotFoundException{
        User user = userDbService.getUser(id).orElseThrow(UserNotFoundException::new);
        user.setStatus(true);
        return userMapper.mapToUserDto(user);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User toSave = userDbService.addNewUser(user);
        return userMapper.mapToUserDto(toSave);
    }

    @DeleteMapping("/delete/{id}")
    public void removeUser(@PathVariable("id") final Long id){
        userDbService.removeUser(id);
    }
}