package com.orderservice.service;

import com.orderservice.request.OrderRequest;

public interface OrderService {
    Integer createdOrder(OrderRequest orderRequest);
}
