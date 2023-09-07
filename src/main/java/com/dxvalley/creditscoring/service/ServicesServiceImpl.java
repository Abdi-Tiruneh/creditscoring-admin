package com.dxvalley.creditscoring.service;

import com.dxvalley.creditscoring.customer.Customer;
import com.dxvalley.creditscoring.customer.CustomerService;
import com.dxvalley.creditscoring.exceptions.customExceptions.ResourceNotFoundException;
import com.dxvalley.creditscoring.model.Model;
import com.dxvalley.creditscoring.model.ModelService;
import com.dxvalley.creditscoring.service.dto.ServiceAddReq;
import com.dxvalley.creditscoring.service.dto.ServiceModelReq;
import com.dxvalley.creditscoring.service.dto.ServiceUpdateReq;
import com.dxvalley.creditscoring.utils.ApiResponse;
import com.dxvalley.creditscoring.utils.CurrentLoggedInUser;
import com.dxvalley.creditscoring.utils.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    private final ServicesRepository servicesRepository;
    private final ModelService modelService;
    private final CustomerService customerService;
    private final CurrentLoggedInUser currentLoggedInUser;

    @Override
    @Transactional
    public Services addService(ServiceAddReq serviceAddReq) {

        Customer customer = customerService.getCustomerById(serviceAddReq.getCustomerId());
        List<Model> selectedModels = modelService.getModelsById(serviceAddReq.getModelIds());

        Services service = new Services();
        service.setName(serviceAddReq.getName());
        service.setDescription(serviceAddReq.getDescription());
        service.setServiceStatus(Status.ACTIVE);
        service.setAddedBy(currentLoggedInUser.getUser().getFullName());
        service.setUpdatedBy(currentLoggedInUser.getUser().getFullName());
        service.setModels(selectedModels);
        service.setCustomer(customer);

        return servicesRepository.save(service);

    }

    @Override
    public Services updateService(Long id, ServiceUpdateReq updateReq) {
        Services service = getService(id);

        if (updateReq.getDescription() != null)
            service.setDescription(updateReq.getDescription());

        if (updateReq.getName() != null)
            service.setName(updateReq.getName());

        service.setUpdatedBy(currentLoggedInUser.getUser().getFullName());
        return servicesRepository.save(service);
    }

    @Override
    public List<Services> getServices() {
        List<Services> services = servicesRepository.findAll();
        if (services.isEmpty())
            throw new ResourceNotFoundException("No services found");

        return services;
    }

    @Override
    public Services getService(Long id) {
        return servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
    }

    @Override
    public Services addModelsToService(Long id, ServiceModelReq updateReq) {
        Services service = getService(id);

        List<Model> modelsToAdd = updateReq.getModels()
                .stream()
                .filter(model -> !service.getModels().contains(model))
                .toList();

        service.getModels().addAll(modelsToAdd);
        service.setUpdatedBy(currentLoggedInUser.getUser().getFullName());

        return servicesRepository.save(service);
    }

    @Override
    public Services removeModelsFromService(Long id, ServiceModelReq updateReq) {
        Services service = getService(id);

        List<Model> modelsToRemove = service.getModels()
                .stream()
                .filter(updateReq.getModels()::contains)
                .toList();

        service.getModels().removeAll(modelsToRemove);
        service.setUpdatedBy(currentLoggedInUser.getUser().getFullName());

        return servicesRepository.save(service);
    }

    @Override
    public Services blockService(Long id) {
        Services service = getService(id);

        service.setServiceStatus(Status.BLOCKED);
        service.setUpdatedBy(currentLoggedInUser.getUser().getFullName());

        return servicesRepository.save(service);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteService(Long id) {
        Services service = getService(id);
        servicesRepository.delete(service);

        return ApiResponse.success("Service deleted successfully");
    }

}
