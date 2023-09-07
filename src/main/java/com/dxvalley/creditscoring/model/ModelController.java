package com.dxvalley.creditscoring.model;

import com.dxvalley.creditscoring.model.dto.ModelAddReq;
import com.dxvalley.creditscoring.model.dto.ModelUpdateReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
@RequiredArgsConstructor
@Tag(name = "Models API. GOOD TO GO")
public class ModelController {

    private final ModelService modelService;

    @GetMapping
    public ResponseEntity<List<Model>> getModels() {
        return ResponseEntity.ok(modelService.getModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        Model model = modelService.getModelById(id);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<Model> addModel(@RequestBody @Valid ModelAddReq modelAddReq) {
        Model model = modelService.addModel(modelAddReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Long id, @RequestBody @Valid ModelUpdateReq modelUpdateReq) {
        Model model = modelService.updateModel(id, modelUpdateReq);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/toggleStatus/{id}")
    @Operation(summary = "Block/Active Model",
            description = "Toggle the status of a model by ID.")
    public ResponseEntity<Model> toggleModelStatus(@PathVariable Long id) {
        Model model = modelService.toggleModelStatus(id);
        return ResponseEntity.ok(model);
    }

}
