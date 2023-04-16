package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartGroupRepositoryTestSuite {

    @Autowired
    private CartGroupRepository cartGroupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateCart() {

        //Given
        User user = new User("John", "Smith", false, 1L);
        Product product1 = new Product();
        Product product2 = new Product();
        Cart cart = new Cart(user, Arrays.asList(product1, product2));

        //When
        userRepository.save(user);
        productRepository.saveAll(Arrays.asList(product1, product2));
        cartGroupRepository.save(cart);

        //Then
        assertEquals(user.getId(), cart.getUser().getId());
        assertEquals(product1.getId(), cart.getProductList().get(0).getId());
        assertEquals(product2.getId(), cart.getProductList().get(1).getId());

        //CleanUp
        cartGroupRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testGetAllCarts() {

        //Given
        User user = new User("John", "Smith", false, 1L);
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Cart cart1 = new Cart(user, Arrays.asList(product1, product2));
        Cart cart2 = new Cart(user, Arrays.asList(product2, product3));
        Cart cart3 = new Cart(user, Arrays.asList(product1, product3));

        //When
        userRepository.save(user);
        productRepository.saveAll(Arrays.asList(product1, product2, product3));
        cartGroupRepository.saveAll(Arrays.asList(cart1, cart2, cart3));
        List<Cart> foundCarts = (List<Cart>) cartGroupRepository.findAll();

        //Then
        assertEquals(3, foundCarts.size());

        //CleanUp
        cartGroupRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testDeleteCart() {

        //Given
        User user = new User("John", "Smith", false, 1L);
        Product product1 = new Product();
        Product product2 = new Product();
        Cart cart = new Cart(user, Arrays.asList(product1, product2));

        //When
        userRepository.save(user);
        productRepository.saveAll(Arrays.asList(product1, product2));
        cartGroupRepository.save(cart);
        cartGroupRepository.deleteById(cart.getId());

        //Then
        assertFalse(cartGroupRepository.findById(cart.getId()).isPresent());

        //CleanUp
        cartGroupRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testAddProductToCart() {

        //Given
        User user = new User("John", "Smith", false, 1L);
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Cart cart = new Cart(user, productList);

        //When
        userRepository.save(user);
        productRepository.saveAll(productList);
        cartGroupRepository.save(cart);

        //Then
        assertEquals(2, cart.getProductList().size());

        productList.add(new Product());
        Cart updatedCart = cartGroupRepository.save(cart);

        assertEquals(cart.getId(), updatedCart.getId());
        assertEquals(3, cart.getProductList().size());
        assertEquals(3, updatedCart.getProductList().size());

        //CleanUp
        cartGroupRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testRemoveProductFromCart() {

        //Given
        User user = new User("John", "Smith", false, 1L);
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Cart cart = new Cart(user, productList);

        //When
        userRepository.save(user);
        productRepository.saveAll(productList);
        cartGroupRepository.save(cart);

        //Then
        assertEquals(2, cart.getProductList().size());
        assertTrue(cart.getProductList().contains(product1));

        cart.getProductList().remove(product1);
        Cart updatedCart = cartGroupRepository.save(cart);

        assertEquals(cart.getId(), updatedCart.getId());
        assertFalse(cart.getProductList().contains(product1));
        assertTrue(cart.getProductList().contains(product2));
        assertEquals(1, cart.getProductList().size());
        assertEquals(1, updatedCart.getProductList().size());

        //CleanUp
        cartGroupRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }
}
