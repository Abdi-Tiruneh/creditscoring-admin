package com.dxvalley.creditscoring.modelsAdjustment;

import com.dxvalley.creditscoring.modelsAdjustment.dto.ModelsAdjustmentAddReq;
import com.dxvalley.creditscoring.modelsAdjustment.dto.ModelAdjustmentUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelsAdjustmentServiceImpl implements ModelsAdjustmentService {

    private final ModelsAdjustmentRepository modelsAdjustmentRepository;

    @Override
    public List<ModelsAdjustment> addModelsAdjustment(List<ModelsAdjustmentAddReq> addReqList) {
        List<ModelsAdjustment> modelsAdjustments = createModelsAdjustments(addReqList);

        // delete all existing ones, create new one
        modelsAdjustmentRepository.deleteByOrganizationIdAndServiceId(addReqList.get(0).getOrganizationId(),
                addReqList.get(0).getServiceId());

        return modelsAdjustmentRepository.saveAll(modelsAdjustments);
    }

    List<ModelsAdjustment> createModelsAdjustments(List<ModelsAdjustmentAddReq> modelsAdjustmentReqList) {

        List<ModelsAdjustment> modelsAdjustments = new ArrayList<>();

        for (ModelsAdjustmentAddReq modelsAdjustmentReq : modelsAdjustmentReqList) {
            ModelsAdjustment modelsAdjustment = new ModelsAdjustment();

            modelsAdjustment.setModelName(modelsAdjustmentReq.getModelName());
            modelsAdjustment.setModelId(modelsAdjustmentReq.getModelId());
            modelsAdjustment.setServiceId(modelsAdjustmentReq.getServiceId());
            modelsAdjustment.setOrganizationId(modelsAdjustmentReq.getOrganizationId());
            modelsAdjustment.setModelContribution(modelsAdjustmentReq.getModelContribution());

            modelsAdjustments.add(modelsAdjustment);
        }

        return modelsAdjustments;
    }

    @Override
    public ModelsAdjustment updateModelsAdjustment(Long id, ModelAdjustmentUpdateReq updateReq) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateModelsAdjustment'");
    }

    @Override
    public List<ModelsAdjustment> getModelsAdjustment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModelsAdjustment'");
    }

    @Override
    public List<ModelsAdjustment> getOrganizationServiceModelsAdjustment(String organizationId, Long serviceId) {
        return modelsAdjustmentRepository.findByOrganizationIdAndServiceId(organizationId, serviceId);
    }

    @Override
    public ModelsAdjustment getModelAdjustment(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModelAdjustment'");
    }

    @Override
    public void deleteModelsAdjustment(Long id) {
        modelsAdjustmentRepository.deleteById(id);
    }

}
