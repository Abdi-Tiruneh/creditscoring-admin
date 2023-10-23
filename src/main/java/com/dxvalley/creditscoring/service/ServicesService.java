package com.dxvalley.creditscoring.service;

import com.dxvalley.creditscoring.service.dto.ServiceAddReq;
import com.dxvalley.creditscoring.service.dto.ServiceModelReq;
import com.dxvalley.creditscoring.service.dto.ServiceUpdateReq;
import com.dxvalley.creditscoring.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServicesService {
    Services addService(ServiceAddReq addReq);

    Services updateService(Long id, ServiceUpdateReq updateReq);

    List<Services> getServices();

    List<Services> getCustomerServices(String organizationId);

    Services getService(Long id);

    Services addModelsToService(Long serviceId, ServiceModelReq updateReq);

    Services removeModelsFromService(Long serviceId, ServiceModelReq updateReq);

    Services toggleServicesStatus(Long id);

    List<Services> getServicesById(List<Long> serviceIds);

    ResponseEntity<ApiResponse> deleteService(Long id);
}
