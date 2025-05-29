package tiago.module2apis.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import tiago.module2apis.dto.CustomerRequest;
import tiago.module2apis.dto.CustomerResponse;
import tiago.module2apis.dto.MessageResponse;
import tiago.module2apis.gateway.CustomerGateway;
import tiago.module2apis.model.Customer;

import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerGateway customerGateway;

    public CustomerService(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Transactional
    public MessageResponse createCustomer(CustomerRequest request) {
        Customer customer = CustomerServiceMapper.requestToModel(request);
        customerGateway.createCustomer(customer);
        return new MessageResponse("Customer created");
    }

    public Page<CustomerResponse> getAllClients(int page, int pageSize) {
        Page<Customer> allCustomers = customerGateway.findAllCustomers(page, pageSize);
        return allCustomers.map(CustomerServiceMapper::modelToResponse);
    }

    public CustomerResponse getCustomer(UUID id) {
        Customer customer = customerGateway.findCustomerById(id);
        return CustomerServiceMapper.modelToResponse(customer);
    }
}
