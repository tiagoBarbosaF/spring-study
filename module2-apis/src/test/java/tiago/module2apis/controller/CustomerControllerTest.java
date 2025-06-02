package tiago.module2apis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tiago.module2apis.dto.CustomerRequest;
import tiago.module2apis.dto.CustomerResponse;
import tiago.module2apis.dto.MessageResponse;
import tiago.module2apis.factory.CustomerFactory;
import tiago.module2apis.service.CustomerService;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@Import(CustomerControllerTest.TestConfig.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerService customerService;

    @Mock
    private CustomerResponse customerResponse;

    @Test
    void test_createCustomer() throws Exception {
        CustomerRequest customerRequestMock = CustomerFactory.createCustomerRequest();
        MessageResponse messageResponse = new MessageResponse("Customer created");

        when(customerService.createCustomer(customerRequestMock)).thenReturn(messageResponse);

        mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerRequestMock)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Customer created"));

        verify(customerService, times(1)).createCustomer(customerRequestMock);
    }

    @Test
    void test_getAllClient() throws Exception {
        int page = 0;
        int pageSize = 10;
        PageImpl<CustomerResponse> customerResponses = new PageImpl<>(List.of(customerResponse, customerResponse));

        when(customerService.getAllClients(page, pageSize)).thenReturn(customerResponses);

        mockMvc.perform(get("/customer/customers")
                .param("page", String.valueOf(page))
                .param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2));

        verify(customerService, times(1)).getAllClients(page, pageSize);
    }

    @Test
    void test_getCustomerById() throws Exception {
        UUID mockId = UUID.randomUUID();
        CustomerResponse customerResponseMock = CustomerFactory.createCustomerResponse(mockId);

        ObjectMapper objMapper = new ObjectMapper();
        objMapper.registerModule(new JavaTimeModule());
        objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String expectedJson = objMapper.writeValueAsString(customerResponseMock);

        when(customerService.getCustomer(mockId)).thenReturn(customerResponseMock);

        mockMvc.perform(get("/customer/id")
                .param("id", mockId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(customerService, times(1)).getCustomer(mockId);
    }

    @Test
    void test_updateCustomer() throws Exception {
        UUID mockId = UUID.randomUUID();
        CustomerRequest customerRequestMock = CustomerFactory.createCustomerRequest();

        when(customerService.updateCustomer(eq(mockId), any(CustomerRequest.class)))
                .thenReturn(new MessageResponse("Customer updated"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String requestJson = mapper.writeValueAsString(customerRequestMock);

        mockMvc.perform(put("/customer")
                        .param("id", mockId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).updateCustomer(eq(mockId), any(CustomerRequest.class));
    }

    @Test
    void test_deleteCustomer() throws Exception {
        UUID mockId = UUID.randomUUID();

        doNothing().when(customerService).deleteCustomer(mockId);

        mockMvc.perform(delete("/customer")
                .param("id", mockId.toString()))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).deleteCustomer(mockId);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        CustomerService customerService() {
            return mock(CustomerService.class);
        }
    }
}