package com.dxvalley.creditscoring.modelsAdjustment;

import com.dxvalley.creditscoring.modelsAdjustment.dto.ModelAdjustmentUpdateReq;
import com.dxvalley.creditscoring.modelsAdjustment.dto.ModelsAdjustmentAddReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modelsAdjustment")
@RequiredArgsConstructor
@Tag(name = "Models adjustment API. GOOD TO GO")
public class ModelsAdjustmentController {

    private final ModelsAdjustmentService modelsAdjustmentService;

    @PostMapping
    public ResponseEntity<List<ModelsAdjustment>> addModelsAdjustment(@RequestBody @Valid List<ModelsAdjustmentAddReq> modelsAdjustmentAddReq) {
        List<ModelsAdjustment> modelsAdjustments = modelsAdjustmentService.addModelsAdjustment(modelsAdjustmentAddReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelsAdjustments);
    }

    @GetMapping
    public ResponseEntity<List<ModelsAdjustment>> getModelsAdjustment() {
        return ResponseEntity.ok(modelsAdjustmentService.getModelsAdjustment());
    }

    @GetMapping("/{organizationId}/{serviceId}")
    public ResponseEntity<List<ModelsAdjustment>> getOrganizationServiceModelsAdjustment(@PathVariable String organizationId, @PathVariable Long serviceId) {
        return ResponseEntity.ok(modelsAdjustmentService.getOrganizationServiceModelsAdjustment(organizationId, serviceId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelsAdjustment> getService(@PathVariable Long id) {
        return ResponseEntity.ok(modelsAdjustmentService.getModelAdjustment(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<ModelsAdjustment> updateModelsAdjustment(@PathVariable Long id, @RequestBody @Valid ModelAdjustmentUpdateReq updateReq) {
        ModelsAdjustment modelsAdjustment = modelsAdjustmentService.updateModelsAdjustment(id, updateReq);
        return ResponseEntity.ok(modelsAdjustment);
    }

    @DeleteMapping("/{id}")
    void deleteModelsAdjustment(@PathVariable Long id) {
        modelsAdjustmentService.deleteModelsAdjustment(id);
    }

}
