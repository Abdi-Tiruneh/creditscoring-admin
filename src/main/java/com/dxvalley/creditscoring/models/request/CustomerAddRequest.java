package com.dxvalley.creditscoring.models.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
public class CustomerAddRequest {

    @NotEmpty(message = "The customer company name is a required field.")
    private String companyName;
    @NotEmpty(message = "The customer email is a required field.")
    private String email;

}
