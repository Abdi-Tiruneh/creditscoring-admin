package com.dxvalley.creditscoring.models.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
public class ModelAddRequest {
    @NotEmpty(message = "The models name is a required field.")
    private String name;
    @NotEmpty(message = "The models description is a required field.")
    private String description;
    @NotEmpty(message = "The models path is a required field.")
    private String path;
}
