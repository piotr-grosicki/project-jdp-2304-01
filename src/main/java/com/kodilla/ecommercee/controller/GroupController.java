package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/shop/groups")
public class GroupController {
    @GetMapping
    public GroupDto getGroups() {
        return new GroupDto(1, "Test - get(GET) all groups");
    }

    @GetMapping(value = "{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return new GroupDto(groupId, "Test - get(GET) single groups");
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(groupDto.getId(), "Test - update(PUT) group");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(1L, "Test - create(PUT) new group");
    }
}
