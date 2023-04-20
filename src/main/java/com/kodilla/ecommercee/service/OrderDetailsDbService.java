package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.OrderDetailsNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderDetails;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsDbService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final CartRepository cartRepository;

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsRepository.findAll();
    }

    public OrderDetails createOrderDetails(Long cartId) throws CartNotFoundException {
        Cart foundCart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);

        OrderDetails orderDetails = new OrderDetails(
                calculateTotalPrice(foundCart),
                foundCart,
                foundCart.getUser()
        );
        orderDetailsRepository.save(orderDetails);
        return orderDetails;
    }

    public OrderDetails getOrderDetails(Long orderDetailsId) throws OrderDetailsNotFoundException {
        return orderDetailsRepository.findById(orderDetailsId).orElseThrow(OrderDetailsNotFoundException::new);
    }

    public OrderDetails updateOrderDetails(OrderDetails newOrderDetails) throws OrderDetailsNotFoundException{
        OrderDetails orderDetails = orderDetailsRepository.findById(newOrderDetails.getId()).orElseThrow(OrderDetailsNotFoundException::new);
        orderDetails.setCart(newOrderDetails.getCart());
        orderDetails.setTotalPrice(calculateTotalPrice(orderDetails.getCart()));
        orderDetails.setUser(newOrderDetails.getUser());
        return orderDetailsRepository.save(orderDetails);
    }
    public void deleteOrderDetails(Long orderDetailsId) throws OrderDetailsNotFoundException{
        if (!orderDetailsRepository.existsById(orderDetailsId)){
            throw new OrderDetailsNotFoundException();
        }
        orderDetailsRepository.deleteById(orderDetailsId);
    }
    private BigDecimal calculateTotalPrice(Cart cart) {

        return BigDecimal.valueOf(cart.getProductList().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum));
    }
}
