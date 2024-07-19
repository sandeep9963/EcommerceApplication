package com.productservice.controller;

import com.productservice.request.ProductPurchaseRequest;
import com.productservice.request.ProductRequest;
import com.productservice.response.ProductPurchaseResponse;
import com.productservice.response.ProductResponse;
import com.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }
    @PostMapping
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody List<ProductPurchaseRequest> requests){
        return ResponseEntity.ok(productService.purchaseProduct(requests));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findbyId(@PathVariable("product-id") Integer productId){
        return ResponseEntity.ok(productService.findById(productId));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
}
