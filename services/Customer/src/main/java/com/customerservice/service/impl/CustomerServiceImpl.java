package com.customerservice.service.impl;

import com.customerservice.repository.CustomerRepository;
import com.customerservice.exception.CustomerNotFoundException;
import com.customerservice.mapper.CustomerMapper;
import com.customerservice.model.Customer;
import com.customerservice.request.CustomerRequest;
import com.customerservice.response.CustomerResponse;
import com.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(
                () -> new CustomerNotFoundException(
                        format("Can not update customer :: No Customer found with provided Id::",request.id())
                )
        );
        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(mapper::fromCustomer).collect(Collectors.toList());
    }

    @Override
    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    @Override
    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer).orElseThrow(
                       () -> new CustomerNotFoundException(format("Customer not found with provided Id::%s",customerId))
                );
    }

    @Override
    public void deleteCustomer(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException(format("Customer not found with Id::%s", customerId))
        );
        customerRepository.delete(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }
}
