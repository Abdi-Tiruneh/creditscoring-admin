package com.dxvalley.creditscoring.models.Entity;

import com.dxvalley.creditscoring.customer.Customer;
import com.dxvalley.creditscoring.service.Services;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customer_services")
public class CustomerService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerServiceId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    private Services services;

    // additional column(s) in the join table
    private String apiKey;
}
