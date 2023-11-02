package com.dxvalley.creditscoring.modelsAdjustment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ModelsAdjustmentRepository extends JpaRepository <ModelsAdjustment, Long> {
    ModelsAdjustment findByModelIdAndOrganizationIdAndServiceId(Long modelId, String organizationId, Long serviceId);

    List<ModelsAdjustment> findByOrganizationIdAndServiceId(String organizationId, Long serviceId);

    @Transactional //delete all
    void deleteByOrganizationIdAndServiceId(String organizationId, Long serviceId);
}
