package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/shop/groups")
public class GroupController {

    @GetMapping
    public GenericEntity getGroups() {
        return new GenericEntity("Test - get(GET) all groups");
    }

    @GetMapping(value = "{groupId}")
    public GenericEntity getGroup(@PathVariable Long groupId) {
        return new GenericEntity("Test - get(GET) single group with id: " + groupId);
    }

    @PutMapping
    public GenericEntity updateGroup() {
        return new GenericEntity("Test - update(PUT) group");
    }

    @PostMapping
    public GenericEntity createGroup() {
        return new GenericEntity("Test - create(POST) group");
    }
}
