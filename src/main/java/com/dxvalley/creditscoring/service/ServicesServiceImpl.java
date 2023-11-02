package com.dxvalley.creditscoring.service;

import com.dxvalley.creditscoring.customer.Customer;
import com.dxvalley.creditscoring.customer.CustomerService;
import com.dxvalley.creditscoring.exceptions.customExceptions.ResourceNotFoundException;
import com.dxvalley.creditscoring.model.Model;
import com.dxvalley.creditscoring.model.ModelService;
import com.dxvalley.creditscoring.modelsAdjustment.ModelsAdjustment;
import com.dxvalley.creditscoring.modelsAdjustment.ModelsAdjustmentService;
import com.dxvalley.creditscoring.modelsAdjustment.dto.ModelsAdjustmentAddReq;
import com.dxvalley.creditscoring.service.dto.ServiceAddReq;
import com.dxvalley.creditscoring.service.dto.ServiceModelReq;
import com.dxvalley.creditscoring.service.dto.ServiceUpdateReq;
import com.dxvalley.creditscoring.utils.ApiResponse;
import com.dxvalley.creditscoring.utils.CurrentLoggedInUser;
import com.dxvalley.creditscoring.utils.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    private final ServicesRepository servicesRepository;
    private final ModelService modelService;
    private final CustomerService customerService;
    private final CurrentLoggedInUser currentLoggedInUser;
    private final ModelsAdjustmentService modelsAdjustmentService;

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

        Services serviceResponse = servicesRepository.save(service);

        List<ModelsAdjustmentAddReq> modelsAdjustmentAddReqList = new ArrayList<>();

        int index = 0;
        for(Model model : selectedModels){
            ModelsAdjustmentAddReq newModelsAdjustmentAddReq = new ModelsAdjustmentAddReq();
            newModelsAdjustmentAddReq.setModelId(model.getId());
            newModelsAdjustmentAddReq.setModelName(model.getName());
            if(index == 0){
                newModelsAdjustmentAddReq.setModelContribution("100");
            }else{
                newModelsAdjustmentAddReq.setModelContribution("0");
            }
            newModelsAdjustmentAddReq.setOrganizationId(customer.getOrganizationId());
            newModelsAdjustmentAddReq.setServiceId(serviceResponse.getId());

            modelsAdjustmentAddReqList.add(newModelsAdjustmentAddReq);

            index++;
        }
        
        List<ModelsAdjustment> ma = modelsAdjustmentService.addModelsAdjustment(modelsAdjustmentAddReqList);

        System.out.println("####################### " + ma);

        return serviceResponse;

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
        List<Services> services = servicesRepository.findAll(Sort.by(Sort.Order.asc("id")));
        if (services.isEmpty())
            throw new ResourceNotFoundException("No services found");

        return services;
    }

    @Override
    public List<Services> getCustomerServices(String organizationId) {
        List<Services> services = servicesRepository.findByCustomerOrganizationId(organizationId);
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
    public Services toggleServicesStatus(Long id) {
        Services service = getService(id);
//
//        if (!service.getUpdatedBy().equals("Customer"))
//            throw new ForbiddenException("Only Bank is allowed to perform this action");

        if (service.getServiceStatus() == Status.ACTIVE)
            service.setServiceStatus(Status.BLOCKED);
        else
            service.setServiceStatus(Status.ACTIVE);

        service.setUpdatedBy("Customer");
        return servicesRepository.save(service);
    }

    @Override
    public List<Services> getServicesById(List<Long> serviceIds) {
        List<Services> services = servicesRepository.findAllById(serviceIds);

        if (services.isEmpty())
            throw new ResourceNotFoundException("No Services is found for the provided IDs");

        return services.stream()
                .sorted(Comparator.comparing(Services::getId))
                .toList();
    }

    @Override
    public ResponseEntity<ApiResponse> deleteService(Long id) {
        Services service = getService(id);
        servicesRepository.delete(service);

        return ApiResponse.success("Service deleted successfully");
    }

}
