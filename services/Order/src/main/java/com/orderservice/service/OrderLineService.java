package com.orderservice.service;

import com.orderservice.request.OrderLineRequest;

public interface OrderLineService {
    public Integer saveOrderLine(OrderLineRequest orderLineRequest);
}
