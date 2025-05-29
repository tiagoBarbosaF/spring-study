package tiago.module2apis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import tiago.module2apis.exception.CustomerException;
import tiago.module2apis.gateway.CustomerGateway;
import tiago.module2apis.model.Customer;

import java.util.UUID;

@Component
public class CustomerGatewayImpl implements CustomerGateway {
    private final CustomerRepository customerRepository;

    public CustomerGatewayImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> findAllCustomers(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return customerRepository.findAll(pageRequest).map(CustomerMapper::entityToModel);
    }

    @Override
    public void createCustomer(Customer customer) {
        CustomerEntity entity = CustomerMapper.modelToEntity(customer);
        customerRepository.save(entity);
    }

    @Override
    public Customer findCustomerById(UUID id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::entityToModel)
                .orElseThrow(() -> new CustomerException("Customer not found"));
    }
}
