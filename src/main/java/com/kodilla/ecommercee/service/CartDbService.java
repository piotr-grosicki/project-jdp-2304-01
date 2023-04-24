package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderDetails;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailsDbService orderDetailsDbService;


    public Cart createCart(Long userId) throws UserNotFoundException {
        User foundUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Cart cart = new Cart(foundUser);
        return repository.save(cart);
    }

    public Cart addToCart(Long cartId, Long productId) throws CartNotFoundException, ProductNotFoundException {
        Product productToAdd = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Cart foundCart = repository.findById(cartId).orElseThrow(CartNotFoundException::new);

        foundCart.getProductList().add(productToAdd);
        return repository.save(foundCart);
    }

    public Cart deleteFromCart(Long cartId, Long productId) throws CartNotFoundException, ProductNotFoundException {
        Product productToRemove = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Cart foundCart = repository.findById(cartId).orElseThrow(CartNotFoundException::new);

        foundCart.getProductList().remove(productToRemove);
        return repository.save(foundCart);
    }

    public Cart getCart(Long cartId) throws CartNotFoundException {
        return repository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public OrderDetails createOrder(Long cartId) throws CartNotFoundException {
        return orderDetailsDbService.createOrderDetails(cartId);
    }
}
