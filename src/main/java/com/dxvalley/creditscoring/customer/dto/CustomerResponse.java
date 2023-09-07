package com.dxvalley.creditscoring.customer.dto;

import com.dxvalley.creditscoring.utils.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String organizationId;
    private String organizationName;
    private String organizationEmail;
    private String organizationPhoneNumber;
    private Status customerStatus;
    private Integer services;
    private String createdBy;
    private String createdAt;
    private String updatedBy;
    private String updatedAt;
}
