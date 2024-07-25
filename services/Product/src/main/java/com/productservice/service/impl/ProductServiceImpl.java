package com.productservice.service.impl;

import com.productservice.exception.ProductPurchaseException;
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

import java.util.ArrayList;
import java.util.Comparator;
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
        var storeProduct = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size()!=storeProduct.size()){
            throw new ProductPurchaseException("One or more products doesn't exists");
        }
        var storedRequest = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchaseProduct = new ArrayList<ProductPurchaseResponse>();
        for (int i= 0; i< storeProduct.size(); i++){
            var product = storeProduct.get(i);
            var productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity()< productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID::"+ productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseProduct.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchaseProduct;
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
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
