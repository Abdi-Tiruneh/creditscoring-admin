package com.dxvalley.creditscoring.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerAddReq {
    @NotBlank(message = "Organization Name is required")
    private String organizationName;

    @NotBlank(message = "Organization Email is required")
    @Email(message = "Invalid email format")
    private String organizationEmail;

    @NotBlank(message = "Organization Phone Number is required")
    @Pattern(
            regexp = "^[0-9]*$", // Only allow digits
            message = "Invalid phone number format"
    )
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String organizationPhoneNumber;
}
