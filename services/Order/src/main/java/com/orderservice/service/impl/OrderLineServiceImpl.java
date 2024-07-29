package com.orderservice.service.impl;

import com.orderservice.mapper.orderLineMapper;
import com.orderservice.repository.OrderLineRepository;
import com.orderservice.request.OrderLineRequest;
import com.orderservice.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final orderLineMapper mapper;
    @Override
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
