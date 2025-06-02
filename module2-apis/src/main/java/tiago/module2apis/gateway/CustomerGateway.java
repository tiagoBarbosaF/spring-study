package tiago.module2apis.gateway;

import org.springframework.data.domain.Page;
import tiago.module2apis.model.Customer;

import java.util.UUID;

public interface CustomerGateway {
    Page<Customer> findAllCustomers(int page, int pageSize);
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    Customer findCustomerById(UUID id);
    void deleteCustomerById(UUID id);
}
