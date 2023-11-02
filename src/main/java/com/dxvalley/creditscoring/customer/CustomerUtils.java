package com.dxvalley.creditscoring.customer;

import com.dxvalley.creditscoring.customer.dto.CustomerAddReq;
import com.dxvalley.creditscoring.emailManager.EmailBuilder;
import com.dxvalley.creditscoring.emailManager.EmailService;
import com.dxvalley.creditscoring.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.dxvalley.creditscoring.utils.CurrentLoggedInUser;
import com.dxvalley.creditscoring.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerUtils {
    private final CustomerRepository customerRepository;
    private final CurrentLoggedInUser currentLoggedInUser;
    private final EmailService emailService;

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public void checkOrganizationEmailExistence(String email) {
        Optional<Customer> existingCustomer = customerRepository.findByOrganizationEmail(email);

        if (existingCustomer.isPresent())
            throw new ResourceAlreadyExistsException("Email is already taken");
    }

    public void checkOrganizationPhoneNumberExistence(String phoneNumber) {
        Optional<Customer> existingCustomer = customerRepository.findByOrganizationPhoneNumber(phoneNumber);

        if (existingCustomer.isPresent())
            throw new ResourceAlreadyExistsException("Phone number is already taken");
    }


    public Customer createCustomer(CustomerAddReq customerAddReq) {
        String organizationId = generateOrganizationId(customerAddReq.getOrganizationName());

        return Customer.builder()
                .organizationId(organizationId)
                .organizationName(customerAddReq.getOrganizationName())
                .organizationEmail(customerAddReq.getOrganizationEmail())
                .organizationPhoneNumber(customerAddReq.getOrganizationPhoneNumber())
                .organizationLocation(customerAddReq.getOrganizationLocation())
                .createdBy(currentLoggedInUser.getUser().getFullName())
                .customerStatus(Status.INITIAL)
                .build();
    }

    public void sendEmailToCustomer(Customer customer) {
        String organizationId = customer.getOrganizationId();
        emailService.send(customer.getOrganizationEmail(), EmailBuilder.emailBuilderForUserConfirmation(customer.getOrganizationId(), "Link baby"), "Credit Score");

    }

    public String generateOrganizationId(String organizationName) {

        String prefix = organizationName.substring(0, 2);

        String organizationId;
        do {
            // Generate 4 random characters
            StringBuilder suffix = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
                char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
                suffix.append(randomChar);
            }

            organizationId = prefix + suffix.toString();
        } while (customerRepository.findByOrganizationId(organizationId).isPresent());

        return organizationId;
    }

}
