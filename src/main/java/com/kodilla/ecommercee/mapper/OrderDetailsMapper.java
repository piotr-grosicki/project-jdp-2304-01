package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.OrderDetails;
import com.kodilla.ecommercee.domain.dto.OrderDetailsDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailsMapper {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderDetailsDto mapToOrderDetailsDto(final OrderDetails orderDetails) {
        return new OrderDetailsDto(
                orderDetails.getId(),
                orderDetails.getTotalPrice(),
                orderDetails.getCart().getId(),
                orderDetails.getUser().getId()
        );
    }
    public List<OrderDetailsDto> mapToOrderDetailsDtoList(final List<OrderDetails> orderDetailsList) {
        return orderDetailsList.stream()
                .map(this::mapToOrderDetailsDto)
                .collect(Collectors.toList());
    }

   public OrderDetails mapToOrderDetails(final OrderDetailsDto orderDetailsDto) throws UserNotFoundException, CartNotFoundException {
        return new OrderDetails(
                orderDetailsDto.getId(),
                orderDetailsDto.getTotalPrice(),
                cartRepository.findById(orderDetailsDto.getCartId()).orElseThrow(CartNotFoundException::new),
                userRepository.findById(orderDetailsDto.getUserId()).orElseThrow(UserNotFoundException::new)
        );
    }
}
