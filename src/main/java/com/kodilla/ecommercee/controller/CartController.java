package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/shop/carts")
public class CartController {

    @GetMapping
    public List<GenericEntity> getCarts() {
        List<GenericEntity> carts = new ArrayList<>();
        carts.add(new GenericEntity("Cart 1"));
        carts.add(new GenericEntity("Cart 2"));
        carts.add(new GenericEntity("Cart 3"));
        return carts;
    }

    @GetMapping("/{id}")
    public GenericEntity getCart(@PathVariable long id) {
        return new GenericEntity("(GET) Cart " + id);
    }

    @PostMapping
    public GenericEntity createCart() {
        return new GenericEntity("(POST) Cart created");
    }

    @PutMapping
    public GenericEntity updateCart() {
        return new GenericEntity("(PUT) Cart edited");
    }

    @DeleteMapping
    public GenericEntity deleteCart() {
        return new GenericEntity("(DELETE) Cart removed");
    }
}
