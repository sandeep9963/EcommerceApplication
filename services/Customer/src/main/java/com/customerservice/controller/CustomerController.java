package com.customerservice.controller;

import com.customerservice.request.CustomerRequest;
import com.customerservice.response.CustomerResponse;
import com.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }
    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("customer-id")String customerId){
        return ResponseEntity.ok(customerService.existById(customerId));
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id")String customerId){
        return new ResponseEntity<>(customerService.findById(customerId),HttpStatus.OK);
    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(@PathVariable("customer-id")String customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
