package com.orderservice.service.impl;

import com.orderservice.config.CustomerClient;
import com.orderservice.config.ProductClient;
import com.orderservice.exception.BusinessException;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.repository.OrderRepository;
import com.orderservice.request.OrderRequest;
import com.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    @Override
    public Integer createdOrder(OrderRequest orderRequest) {
        // Check the customer --> Openfeign
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Can't create order:: No customer exists with the provided Id"));
        //Purchase the product --> Product-ms (RestTemplate)
        this.productClient.purchaseProducts(orderRequest.products());
        //persist the order
        var order = orderRepository.save(mapper.toOrder(orderRequest));
        //persist the order lines
        // start payment process
        //send the order Confirmation --> notification-ms(Kafka)
        return null;
    }
}
