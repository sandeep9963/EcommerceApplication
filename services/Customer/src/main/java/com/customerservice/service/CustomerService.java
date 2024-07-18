package com.customerservice.service;

import com.customerservice.model.Customer;
import com.customerservice.request.CustomerRequest;
import com.customerservice.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    Boolean existById(String customerId);

    CustomerResponse findById(String customerId);

    void deleteCustomer(String customerId);
}
