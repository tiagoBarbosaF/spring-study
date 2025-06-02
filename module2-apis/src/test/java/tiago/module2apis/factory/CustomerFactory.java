package tiago.module2apis.factory;


import tiago.module2apis.dto.CustomerRequest;
import tiago.module2apis.dto.CustomerResponse;
import tiago.module2apis.model.Customer;

import java.time.Instant;
import java.util.UUID;

//@Component
public class CustomerFactory {

    public static CustomerRequest createCustomerRequest() {
        return CustomerRequest.builder()
                .name("test")
                .email("test@test")
                .age(30)
                .build();
    }

    public static Customer createCustomer(UUID id) {
        return Customer.builder()
                .id(id)
                .name("test")
                .email("test@test")
                .age(30)
                .createdAt(Instant.now())
                .build();
    }

    public static CustomerResponse createCustomerResponse(UUID id) {
        return CustomerResponse.builder()
                .id(id)
                .name("test")
                .email("test@test")
                .age(30)
                .createdAt(Instant.now())
                .build();
    }
}
