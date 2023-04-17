package com.kodilla.ecommercee.controller.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleProductGroupNotFoundException(ProductGroupNotFoundException exception) {
        return ResponseEntity.badRequest().body("Group with given id does not exist");
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.badRequest().body("User with given id does not exist");
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.badRequest().body("Product with given id does not exist");
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException exception) {
        return ResponseEntity.badRequest().body("Cart with given id does not exist");
    }
}
