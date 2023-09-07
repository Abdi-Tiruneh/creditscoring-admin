package com.dxvalley.creditscoring.customer;

import com.dxvalley.creditscoring.customer.dto.CustomerAddReq;
import com.dxvalley.creditscoring.customer.dto.CustomerMapper;
import com.dxvalley.creditscoring.customer.dto.CustomerResponse;
import com.dxvalley.creditscoring.customer.dto.CustomerUpdateReq;
import com.dxvalley.creditscoring.exceptions.customExceptions.ResourceNotFoundException;
import com.dxvalley.creditscoring.utils.ApiResponse;
import com.dxvalley.creditscoring.utils.CurrentLoggedInUser;
import com.dxvalley.creditscoring.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerUtils customerUtils;
    private final CurrentLoggedInUser currentLoggedInUser;

    @Override
    public Customer addCustomer(CustomerAddReq addReq) {
        customerUtils.checkOrganizationEmailExistence(addReq.getOrganizationEmail());
        customerUtils.checkOrganizationPhoneNumberExistence(addReq.getOrganizationPhoneNumber());

        Customer customer = customerUtils.createCustomer(addReq);
        Customer savedCustomer = customerRepository.save(customer);

        customerUtils.sendEmailToCustomer(savedCustomer);

        return savedCustomer;
    }

    @Override
    public Customer editCustomer(Long id, CustomerUpdateReq updateReq) {
        Customer customer = getCustomerById(id);

        if (updateReq.getOrganizationName() != null)
            customer.setOrganizationName(updateReq.getOrganizationName());

        // Update email if provided and different from the current email
        if (updateReq.getOrganizationEmail() != null && !customer.getOrganizationEmail().equalsIgnoreCase(updateReq.getOrganizationEmail())) {
            customerUtils.checkOrganizationEmailExistence(updateReq.getOrganizationEmail());
            customer.setOrganizationEmail(updateReq.getOrganizationEmail());
        }

        // Update phone number if provided and different from the current phone number
        if (updateReq.getOrganizationPhoneNumber() != null && !customer.getOrganizationPhoneNumber().equalsIgnoreCase(updateReq.getOrganizationPhoneNumber())) {
            customerUtils.checkOrganizationPhoneNumberExistence(updateReq.getOrganizationPhoneNumber());
            customer.setOrganizationPhoneNumber(updateReq.getOrganizationPhoneNumber());
        }

        customer.setUpdatedBy(currentLoggedInUser.getUser().getFullName());
        return customerRepository.save(customer);
    }

    @Override
    public Customer activateCustomer(String organizationId) {
        Customer customer = customerRepository.findByOrganizationId(organizationId).
                orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setCustomerStatus(Status.ACTIVE);
        customer.setUpdatedBy("Customer");
        return customerRepository.save(customer);
    }

    @Override
    public Customer toggleCustomerStatus(Long id) {
        Customer customer = getCustomerById(id);

        if (customer.getCustomerStatus() == Status.BLOCKED)
            customer.setCustomerStatus(Status.ACTIVE);
        else
            customer.setCustomerStatus(Status.BLOCKED);

        customer.setUpdatedBy(currentLoggedInUser.getUser().getFullName());
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty())
            throw new ResourceNotFoundException("No customers found");

        return customers.stream().map(CustomerMapper::customerResponse).toList();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }


    //make it soft delete
    @Override
    public ResponseEntity<ApiResponse> delete(Long id) {
        getCustomerById(id);
        customerRepository.deleteById(id);
        return ApiResponse.success("Customer deleted successfully");
    }

}
