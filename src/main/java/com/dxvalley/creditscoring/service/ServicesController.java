package com.dxvalley.creditscoring.service;

import com.dxvalley.creditscoring.service.dto.ServiceAddReq;
import com.dxvalley.creditscoring.service.dto.ServiceModelReq;
import com.dxvalley.creditscoring.service.dto.ServiceUpdateReq;
import com.dxvalley.creditscoring.utils.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
@Tag(name = "Services API. GOOD TO GO")
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping
    public ResponseEntity<Services> addService(@RequestBody @Valid ServiceAddReq serviceAddReq) {
        Services services = servicesService.addService(serviceAddReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(services);
    }

    @GetMapping
    public ResponseEntity<List<Services>> getServices() {
        return ResponseEntity.ok(servicesService.getServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Services> getService(@PathVariable Long id) {
        return ResponseEntity.ok(servicesService.getService(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Services> updateService(@PathVariable Long id, @RequestBody @Valid ServiceUpdateReq updateReq) {
        Services services = servicesService.updateService(id, updateReq);
        return ResponseEntity.ok(services);
    }

    @PutMapping("/{id}/addModel")
    public ResponseEntity<Services> addModelToService(@PathVariable Long id, @RequestBody @Valid ServiceModelReq updateReq) {
        return ResponseEntity.ok(servicesService.addModelsToService(id, updateReq));
    }

    @PutMapping("/{id}/removeModel")
    public ResponseEntity<Services> removeModelFromService(@PathVariable Long id, @RequestBody @Valid ServiceModelReq updateReq) {
        return ResponseEntity.ok(servicesService.removeModelsFromService(id, updateReq));
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<Services> blockService(@PathVariable Long id) {
        return ResponseEntity.ok(servicesService.blockService(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteService(@PathVariable Long id) {
        return servicesService.deleteService(id);
    }

}
