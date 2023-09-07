//package com.dxvalley.creditscoring.services.impl;
//
//import java.util.*;
//
//import org.slf4j.*;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.stereotype.Services;
//
//import com.dxvalley.creditscoring.exceptions.ResourceNotFoundException;
//import com.dxvalley.creditscoring.service.Services;
//import com.dxvalley.creditscoring.customer.Customer;
//import com.dxvalley.creditscoring.models.Entity.CustomerService;
//import com.dxvalley.creditscoring.models.request.CustomerAddRequest;
//import com.dxvalley.creditscoring.models.response.ApiResponse;
//import com.dxvalley.creditscoring.service.ServicesRepository;
//import com.dxvalley.creditscoring.customer.CustomerRepository;
//import com.dxvalley.creditscoring.customer.CustomerServicesRepository;
//import com.dxvalley.creditscoring.services.CustomerServicesService;
//
//import lombok.RequiredArgsConstructor;
//
//@Services
//@RequiredArgsConstructor
//public class CustomerServicesServiceImpl implements CustomerServicesService {
//    private final CustomerRepository customerRepository;
//    private final ServicesRepository servicesRepository;
//    private final CustomerServicesRepository customerServiceRepository;
//
//    private final Logger logger = LoggerFactory.getLogger(CustomerServicesServiceImpl.class);
//
//    @Override
//    public ApiResponse<?> addServiceToCustomer(Integer customerId, Integer serviceId) {
//
//        try {
//            Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer does not found!"));
//            Services services = servicesRepository.findById(serviceId)
//                    .orElseThrow(() -> new ResourceNotFoundException("Services does not found!"));
//
//            CustomerService customerService = new CustomerService();
//            customerService.setCustomer(customer);
//            customerService.setServices(services);
//            customerService.setApiKey(generateApiKey());
//
//
//            customerServiceRepository.save(customerService);
//
//            logger.info("service added successfully for a customer");
//            return new ApiResponse<>(200, "service added successfully for a customer", null);
//        } catch (ResourceNotFoundException e) {
//            return new ApiResponse<>(404, e.getMessage(), null);
//        }catch (Exception e) {
//            return new ApiResponse<>(500, e.getMessage(), null);
//        }
//
//    }
//
//    private String generateApiKey() {
//        return "576qjw.weqwe2321.d23eadasda";
//    }
//
//
//
//
//}
