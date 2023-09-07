package com.dxvalley.creditscoring.model;

import com.dxvalley.creditscoring.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status modelStatus;

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
}
