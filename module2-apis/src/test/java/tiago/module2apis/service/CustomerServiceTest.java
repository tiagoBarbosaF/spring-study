package tiago.module2apis.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import tiago.module2apis.dto.CustomerRequest;
import tiago.module2apis.dto.CustomerResponse;
import tiago.module2apis.dto.MessageResponse;
import tiago.module2apis.factory.CustomerFactory;
import tiago.module2apis.gateway.CustomerGateway;
import tiago.module2apis.model.Customer;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerGateway customerGateway;
    @Mock
    private Customer customer;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void test_createCustomer() {
        CustomerRequest mockCustomerRequest = CustomerFactory.createCustomerRequest();

        doNothing().when(customerGateway).createCustomer(any(Customer.class));

        MessageResponse response = customerService.createCustomer(mockCustomerRequest);

        assertNotNull(response);
        assertEquals("Customer created", response.message());

        verify(customerGateway, times(1)).createCustomer(any(Customer.class));
    }

    @Test
    void test_getAllClients() {
        int page = 0;
        int pageSize = 10;
        PageImpl<Customer> mockPage = new PageImpl<>(List.of(customer, customer));

        when(customerGateway.findAllCustomers(page, pageSize)).thenReturn(mockPage);

        Page<CustomerResponse> response = customerService.getAllClients(page, pageSize);

        assertNotNull(response);
        assertEquals(2, response.getSize());

        verify(customerGateway, times(1)).findAllCustomers(page, pageSize);
    }

    @Test
    void test_getCustomer() {
        UUID mockID = UUID.randomUUID();
        Customer customerMock = CustomerFactory.createCustomer(mockID);

        when(customerGateway.findCustomerById(mockID)).thenReturn(customerMock);

        CustomerResponse response = customerService.getCustomer(mockID);

        assertNotNull(response);
        assertEquals(mockID, response.id());

        verify(customerGateway, times(1)).findCustomerById(mockID);
    }

    @Test
    void test_updateCustomer() {
        UUID mockID = UUID.randomUUID();
        CustomerRequest mockCustomerRequest = CustomerFactory.createCustomerRequest();
        Customer customerMock = CustomerFactory.createCustomer(mockID);

        when(customerGateway.findCustomerById(mockID)).thenReturn(customerMock);
        doNothing().when(customerGateway).updateCustomer(any(Customer.class));

        MessageResponse response = customerService.updateCustomer(mockID, mockCustomerRequest);

        assertNotNull(response);
        assertEquals("Customer updated", response.message());

        verify(customerGateway, times(1)).findCustomerById(mockID);
        verify(customerGateway, times(1)).updateCustomer(any(Customer.class));
    }

    @Test
    void test_deleteCustomer() {
        UUID mockID = UUID.randomUUID();

        doNothing().when(customerGateway).deleteCustomerById(mockID);

        customerService.deleteCustomer(mockID);

        verify(customerGateway, times(1)).deleteCustomerById(mockID);
    }

}