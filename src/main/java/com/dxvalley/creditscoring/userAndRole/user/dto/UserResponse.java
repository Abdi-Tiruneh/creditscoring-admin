package com.dxvalley.creditscoring.userAndRole.user.dto;

import com.dxvalley.creditscoring.utils.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String lastLogin;
    private String role;
    private Status status;
    private String enabled;
    private String addedAt;
    private String updatedAt;
    private String addedBy;
    private String updatedBy;
}
