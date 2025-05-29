package tiago.module2apis.service;

import tiago.module2apis.dto.CustomerRequest;
import tiago.module2apis.dto.CustomerResponse;
import tiago.module2apis.model.Customer;

public class CustomerServiceMapper {

    public static CustomerResponse modelToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .age(customer.getAge())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    public static Customer requestToModel(CustomerRequest request) {
        return Customer.builder()
                .name(request.name())
                .email(request.email())
                .age(request.age())
                .build();
    }
}
