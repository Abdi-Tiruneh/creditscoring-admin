package com.dxvalley.creditscoring.customer;

import com.dxvalley.creditscoring.service.Services;
import com.dxvalley.creditscoring.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String organizationId;

    @Column(nullable = false)
    private String organizationName;

    @Column(nullable = false, unique = true)
    private String organizationEmail;

    @Column(nullable = false, unique = true)
    private String organizationPhoneNumber;

    @Column(nullable = true)
    private String organizationLocation;

    @Enumerated(EnumType.STRING)
    private Status customerStatus;

    @Column(nullable = false, updatable = false)
    private String createdBy;

    @Column(updatable = false)
    private String createdAt;

    private String updatedBy;

    private String updatedAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Services> services;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now().toString();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now().toString();
    }

}
