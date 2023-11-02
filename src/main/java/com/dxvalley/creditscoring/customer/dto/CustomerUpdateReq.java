package com.dxvalley.creditscoring.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerUpdateReq {
    private String organizationName;
    private String organizationLocation;
    @Email(message = "Invalid email format")
    private String organizationEmail;

    @Pattern(
            regexp = "^[0-9]*$", // Only allow digits
            message = "Invalid phone number format"
    )
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String organizationPhoneNumber;
}
