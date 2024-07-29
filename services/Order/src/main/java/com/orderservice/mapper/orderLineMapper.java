package com.orderservice.mapper;

import com.orderservice.model.Order;
import com.orderservice.model.OrderLine;
import com.orderservice.request.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class orderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.id())
                                .build()
                )
                .productId(orderLineRequest.productId())

                .build();
    }
}
