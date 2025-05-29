package tiago.module2apis.repository;

import tiago.module2apis.model.Customer;

public class CustomerMapper {

    public static CustomerEntity modelToEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .age(customer.getAge())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    public static Customer entityToModel(CustomerEntity entity) {
        return Customer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .age(entity.getAge())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
