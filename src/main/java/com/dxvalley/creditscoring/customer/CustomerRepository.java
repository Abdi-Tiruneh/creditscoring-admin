package com.dxvalley.creditscoring.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByOrganizationEmail(String organizationEmail);

    Optional<Customer> findByOrganizationPhoneNumber(String organizationPhoneNumber);

    Optional<Customer> findByOrganizationId(String organizationId);
}
