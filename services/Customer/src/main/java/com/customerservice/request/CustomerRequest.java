package com.customerservice.request;

import com.customerservice.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
   String id,
   @NotNull(message = "Customer firstname is required")
   String firstName,
   @NotNull(message = "customer lastname is required")
   String lastName,
   @NotNull(message = "Customer email is required")
   @Email(message = "Customer Email address is not valid")
   String email,
   Address address
) {
}
