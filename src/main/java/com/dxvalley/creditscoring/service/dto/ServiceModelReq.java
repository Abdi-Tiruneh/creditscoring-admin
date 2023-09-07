package com.dxvalley.creditscoring.service.dto;

import com.dxvalley.creditscoring.model.Model;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class ServiceModelReq {

    @Size(min = 1, message = "At least one model must be selected")
    private List<Model> models;
}
