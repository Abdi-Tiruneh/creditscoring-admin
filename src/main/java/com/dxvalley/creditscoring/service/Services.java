package com.dxvalley.creditscoring.service;

import com.dxvalley.creditscoring.customer.Customer;
import com.dxvalley.creditscoring.model.Model;
import com.dxvalley.creditscoring.utils.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        private String description;

        @Enumerated(EnumType.STRING)
        private Status serviceStatus;

        @Column(updatable = false)
        private String addedBy;

        private String addedAt;

        private String updatedBy;

        private String updatedAt;

        @PrePersist
        protected void onCreate() {
                addedAt = LocalDateTime.now().toString();
                updatedAt = addedAt;
        }

        @PreUpdate
        protected void onUpdate() {
                updatedAt = LocalDateTime.now().toString();
        }

        @ManyToMany
        @JoinTable(
                name = "service_model",
                joinColumns = @JoinColumn(name = "service_id"),
                inverseJoinColumns = @JoinColumn(name = "model_id")
        )
        private List<Model> models = new ArrayList<>();

        @ManyToOne
        @JoinColumn(referencedColumnName = "id")
        @JsonIgnore
        private Customer customer;

}
