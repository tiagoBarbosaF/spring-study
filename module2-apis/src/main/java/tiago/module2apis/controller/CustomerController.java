package tiago.module2apis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tiago.module2apis.dto.CustomerRequest;
import tiago.module2apis.dto.CustomerResponse;
import tiago.module2apis.dto.MessageResponse;
import tiago.module2apis.service.CustomerService;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        MessageResponse response = customerService.createCustomer(request);
        return response != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("/customers")
    public ResponseEntity<Page<CustomerResponse>> getClient(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int pageSize) {
        Page<CustomerResponse> response = customerService.getAllClients(page, pageSize);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping("/id")
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestParam UUID id) {
        CustomerResponse response = customerService.getCustomer(id);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
}
