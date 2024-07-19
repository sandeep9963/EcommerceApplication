package com.productservice.service.impl;

import com.productservice.mapper.ProductMapper;
import com.productservice.repository.ProductRepository;
import com.productservice.request.ProductPurchaseRequest;
import com.productservice.request.ProductRequest;
import com.productservice.response.ProductPurchaseResponse;
import com.productservice.response.ProductResponse;
import com.productservice.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        var product = mapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests) {
        var productIds = requests
                .stream().map(ProductPurchaseRequest::productId)
                .toList();
        var storeProductIds = productRepository.findAllByIdInOrderById(productIds);
        return null;
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("Product not found with Id::"+ productId)
                );
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
