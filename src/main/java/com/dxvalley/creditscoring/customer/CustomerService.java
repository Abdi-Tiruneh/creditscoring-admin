package com.dxvalley.creditscoring.customer;

import com.dxvalley.creditscoring.customer.dto.CustomerAddReq;
import com.dxvalley.creditscoring.customer.dto.CustomerResponse;
import com.dxvalley.creditscoring.customer.dto.CustomerUpdateReq;
import com.dxvalley.creditscoring.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(CustomerAddReq customerAddReq);

    List<CustomerResponse> getCustomers();

    Customer editCustomer(Long id, CustomerUpdateReq customerUpdateReq);

    Customer getCustomerById(Long id);

    ResponseEntity<ApiResponse> delete(Long id);

    Customer activateCustomer(String organizationId);

    Customer toggleCustomerStatus(Long id);
}
