package com.productservice.request;

import jakarta.validation.constraints.NotNull;
public record ProductPurchaseRequest(
        @NotNull(message = "Product Id is mandatory")
        Integer productId,
        @NotNull(message = "Product quantity is mandatory")
        double quantity
) {
}
