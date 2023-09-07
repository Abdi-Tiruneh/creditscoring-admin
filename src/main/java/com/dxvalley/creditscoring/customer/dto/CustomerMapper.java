package com.dxvalley.creditscoring.customer.dto;

import com.dxvalley.creditscoring.customer.Customer;

public class CustomerMapper {
    public static CustomerResponse customerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .organizationId(customer.getOrganizationId())
                .organizationName(customer.getOrganizationName())
                .organizationEmail(customer.getOrganizationEmail())
                .organizationPhoneNumber(customer.getOrganizationPhoneNumber())
                .customerStatus(customer.getCustomerStatus())
                .services(customer.getServices().size())
                .createdBy(customer.getCreatedBy())
                .createdAt(customer.getCreatedAt())
                .updatedBy(customer.getUpdatedBy())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }
}

