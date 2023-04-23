package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.OrderDetails;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  OrderDetailsRepository orderDetailsRepository;

    @Test
    public void testSaveUser(){
        //Given
        User user = new User("Test - firstName", "Test - lastName", true);

        //When
        userRepository.save(user);

        //Then
        assertTrue(userRepository.findById(user.getId()).isPresent());

        //Clean up
        userRepository.deleteAll();
    }

    @Test
    public void testFindAllUsers(){
        //Given
        User user1 = new User("Test - firstName1", "Test - lastName1", true);
        User user2 = new User("Test - firstName2", "Test - lastName2", false);

        //When
        userRepository.save(user1);
        userRepository.save(user2);

        //Then
        assertEquals(2,((List<User>)userRepository.findAll()).size());

        //Clean up
        userRepository.deleteAll();
    }

    @Test
    public void testDeleteUser(){
        //Given
        User user1 = new User("Test - firstName1", "Test - lastName1", true);
        User user2 = new User("Test - firstName2", "Test - lastName2", false);

        //When
        userRepository.save(user1);
        userRepository.save(user2);

        long beforeDelete = userRepository.count();
        userRepository.delete(user2);

        //Then
        assertNotEquals(beforeDelete,userRepository.count());

        //Clean up
        userRepository.deleteAll();
    }

    @Test
    public void testAddOrderDetailsToUser(){
        //Given
        User user = new User("Test - firstName", "Test - lastName", true);
        OrderDetails orderDetails1 = new OrderDetails();
        OrderDetails orderDetails2 = new OrderDetails();

        //When
        userRepository.save(user);
        orderDetailsRepository.save(orderDetails1);
        orderDetailsRepository.save(orderDetails2);

        user.getOrders().add(orderDetails1);
        user.getOrders().add(orderDetails2);

        //Then
        assertEquals(2,userRepository.findById(user.getId()).get().getOrders().size());

        //Clean up
        userRepository.deleteAll();
        orderDetailsRepository.deleteAll();
    }

    @Test
    public void testIfOrderDetailsIsRemoved(){
        //Given
        User user = new User("Test - firstName", "Test - lastName", true);
        OrderDetails orderDetails = new OrderDetails();

        //When
        userRepository.save(user);
        orderDetailsRepository.save(orderDetails);
        user.getOrders().add(orderDetails);
        orderDetailsRepository.deleteById(orderDetails.getId());

        //Then
        assertTrue(userRepository.findById(user.getId()).isPresent());

        //Clean up
        userRepository.deleteAll();
        orderDetailsRepository.deleteAll();
    }

    @Test
    public void testIfUserIsRemoved(){
        //Given
        User user = new User("Test - firstName", "Test - lastName", true);
        OrderDetails orderDetails = new OrderDetails();

        //When
        userRepository.save(user);
        orderDetailsRepository.save(orderDetails);
        user.getOrders().add(orderDetails);
        userRepository.deleteById(user.getId());

        //Then
        assertFalse(orderDetailsRepository.findById(orderDetails.getId()).isPresent());

        //Clean up
        userRepository.deleteAll();
        orderDetailsRepository.deleteAll();
    }
}
