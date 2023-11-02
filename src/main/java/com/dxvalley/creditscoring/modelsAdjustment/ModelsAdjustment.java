package com.dxvalley.creditscoring.modelsAdjustment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "models_adjustments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelsAdjustment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_id", nullable = false)
    private Long modelId;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "model_contribution", nullable = false)
    private String modelContribution;
}
