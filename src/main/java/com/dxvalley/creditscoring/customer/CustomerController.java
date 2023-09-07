package com.dxvalley.creditscoring.customer;

import com.dxvalley.creditscoring.customer.dto.CustomerAddReq;
import com.dxvalley.creditscoring.customer.dto.CustomerResponse;
import com.dxvalley.creditscoring.customer.dto.CustomerUpdateReq;
import com.dxvalley.creditscoring.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customers API (organizations). GOOD TO GO")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid CustomerAddReq customerAddReq) {
        Customer customer = customerService.addCustomer(customerAddReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Customer> editCustomer(@PathVariable Long id, @RequestBody CustomerUpdateReq customerUpdateReq) {
        return ResponseEntity.ok(customerService.editCustomer(id, customerUpdateReq));
    }

    @PutMapping("/activate/{organizationId}")
    ResponseEntity<Customer> activateCustomer(@PathVariable String organizationId) {
        return ResponseEntity.ok(customerService.activateCustomer(organizationId));
    }

    @PutMapping("/toggleStatus/{id}")
    @Operation(summary = "Block/Active Customer",
            description = "Toggle the status of a Customer by ID.")
    public ResponseEntity<Customer> toggleCustomerStatus(@PathVariable Long id) {
        Customer customer = customerService.toggleCustomerStatus(id);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) {
        return customerService.delete(id);
    }
}
