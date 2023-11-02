package com.dxvalley.creditscoring.modelsAdjustment;

import com.dxvalley.creditscoring.modelsAdjustment.dto.ModelsAdjustmentAddReq;
import com.dxvalley.creditscoring.modelsAdjustment.dto.ModelAdjustmentUpdateReq;
import java.util.List;

public interface ModelsAdjustmentService {
    List<ModelsAdjustment> addModelsAdjustment(List<ModelsAdjustmentAddReq> addReq);

    ModelsAdjustment updateModelsAdjustment(Long id, ModelAdjustmentUpdateReq updateReq);

    List<ModelsAdjustment> getModelsAdjustment();

    List<ModelsAdjustment> getOrganizationServiceModelsAdjustment(String organizationId, Long serviceId);

    ModelsAdjustment getModelAdjustment(Long id);

    void deleteModelsAdjustment(Long id);
}
