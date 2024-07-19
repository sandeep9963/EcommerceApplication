package com.productservice.service;

import com.productservice.request.ProductPurchaseRequest;
import com.productservice.request.ProductRequest;
import com.productservice.response.ProductPurchaseResponse;
import com.productservice.response.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer createProduct(ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
