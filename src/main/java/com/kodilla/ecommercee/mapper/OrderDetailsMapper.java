package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderDetails;
import com.kodilla.ecommercee.domain.dto.OrderDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsMapper {

    public OrderDetailsDto mapToOrderDetailsDto(OrderDetails orderDetails) {
        return new OrderDetailsDto(
                orderDetails.getId(),
                orderDetails.getTotalPrice()
        );
    }
}
