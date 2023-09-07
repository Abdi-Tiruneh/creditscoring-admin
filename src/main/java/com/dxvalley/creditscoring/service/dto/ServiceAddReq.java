package com.dxvalley.creditscoring.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ServiceAddReq {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Size(min = 1, message = "At least one model must be selected")
    private List<Long> modelIds;

    @NotNull(message = "Customer ID is required")
    @Positive(message = "Customer ID must be a positive number")
    private Long customerId;

}
