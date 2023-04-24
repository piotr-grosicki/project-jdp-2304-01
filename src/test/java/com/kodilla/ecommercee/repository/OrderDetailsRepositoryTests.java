package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderDetailsRepositoryTests {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartGroupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveOrderDetails() {
        //Given
        User user = new User("Test - firstName", "Test - lastName", "Test - login", true);
        Cart cart = new Cart();
        OrderDetails orderDetails = new OrderDetails(BigDecimal.ONE,cart, user);

        //When
        userRepository.save(user);
        cartGroupRepository.save(cart);
        orderDetailsRepository.save(orderDetails);

        //Then
        assertTrue(orderDetailsRepository.findById(orderDetails.getId()).isPresent());
        assertTrue(orderDetailsRepository.findById(orderDetails.getId()).get().getUser().isBlocked());

        //Clean up
        userRepository.deleteAll();
        cartGroupRepository.deleteAll();
        orderDetailsRepository.deleteAll();
    }

    @Test
    public void testDownloadElementsFromCart() {
        //Given
        User user = new User("Test - firstName", "Test - lastName", "Test - login", true);
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Cart cart = new Cart(user, productList);
        OrderDetails orderDetails = new OrderDetails(BigDecimal.ONE,cart, user);

        //When
        userRepository.save(user);
        productRepository.save(product1);
        productRepository.save(product2);
        cartGroupRepository.save(cart);
        orderDetailsRepository.save(orderDetails);

        //Then
        assertTrue(orderDetailsRepository.findById(orderDetails.getId()).isPresent());
        assertEquals(2,orderDetailsRepository.findById(orderDetails.getId()).get().getCart().getProductList().size());

        //Clean up
        userRepository.deleteAll();
        productRepository.save(product1);
        productRepository.save(product2);
        cartGroupRepository.deleteAll();
        orderDetailsRepository.deleteAll();
    }

    @Test
    public void testDeleteOrderDetails() {
        //Given
        User user1 = new User("Test - firstName", "Test - lastName", "Test - login", true);
        Cart cart1 = new Cart();
        OrderDetails orderDetails1 = new OrderDetails(BigDecimal.ONE, cart1, user1);

        User user2 = new User("Test - firstName", "Test - lastName", "Test - login", true);
        Cart cart2 = new Cart();
        OrderDetails orderDetails2 = new OrderDetails(BigDecimal.TEN, cart2 ,user2);

        //When
        userRepository.save(user1);
        cartGroupRepository.save(cart1);
        orderDetailsRepository.save(orderDetails1);

        userRepository.save(user1);
        cartGroupRepository.save(cart2);
        orderDetailsRepository.save(orderDetails2);

        long orderDetailsListSize = orderDetailsRepository.count();
        orderDetailsRepository.deleteById(orderDetails1.getId());

        //Then
        assertEquals(orderDetailsListSize - 1, orderDetailsRepository.count());

        //Clean up
        userRepository.deleteAll();
        cartGroupRepository.deleteAll();
        orderDetailsRepository.deleteAll();
    }

    @Test
    public void testIfOrderDetailsIsRemoved() {
        //Given
        User user = new User("Test - firstName", "Test - lastName", "Test - login", true);
        Cart cart = new Cart();
        OrderDetails orderDetails = new OrderDetails(BigDecimal.ONE,cart, user);

        //When
        userRepository.save(user);
        cartGroupRepository.save(cart);
        orderDetailsRepository.save(orderDetails);

        orderDetailsRepository.deleteById(orderDetails.getId());

        //Then
        assertFalse(cartGroupRepository.findById(cart.getId()).isPresent());
        assertTrue(userRepository.findById(user.getId()).isPresent());

        //Clean up
        userRepository.deleteAll();
        cartGroupRepository.deleteAll();
    }

    @Test
    public void testIfCartIsRemoved() {
        //Given
        User user = new User("Test - firstName", "Test - lastName", "Test - login", true);
        Cart cart = new Cart();
        OrderDetails orderDetails = new OrderDetails(BigDecimal.ONE,cart, user);

        //When
        userRepository.save(user);
        cartGroupRepository.save(cart);
        orderDetailsRepository.save(orderDetails);

        cartGroupRepository.deleteById(cart.getId());

        //Then
        assertTrue(orderDetailsRepository.findById(orderDetails.getId()).isPresent());


        //Clean up
        userRepository.deleteAll();
        orderDetailsRepository.deleteAll();
    }

    @Test
    public void testIfUserIsRemoved(){
        //Given
        User user = new User("Test - firstName", "Test - lastName", "Test - login", true);
        Cart cart = new Cart();
        OrderDetails orderDetails = new OrderDetails(BigDecimal.ONE,cart, user);

        //When
        userRepository.save(user);
        cartGroupRepository.save(cart);
        orderDetailsRepository.save(orderDetails);

        userRepository.deleteById(user.getId());

        //Then
        assertTrue(orderDetailsRepository.findById(orderDetails.getId()).isPresent());

        //Clean up
        orderDetailsRepository.deleteAll();
        cartGroupRepository.deleteAll();
    }

}
