package com.dxvalley.creditscoring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository <Services, Long> {
    List<Services> findByCustomerOrganizationId(String organizationId);
}
