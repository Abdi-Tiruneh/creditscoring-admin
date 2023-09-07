package com.dxvalley.creditscoring.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ModelAddReq {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than or equal to 255 characters")
    private String name;

    @NotBlank(message = "Path is required")
    @Size(max = 255, message = "Path must be less than or equal to 255 characters")
    private String path;

    @Size(max = 500, message = "Description must be less than or equal to 500 characters")
    private String description;
}
