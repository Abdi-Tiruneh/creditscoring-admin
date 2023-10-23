package com.dxvalley.creditscoring.userAndRole.user.dto;

import com.dxvalley.creditscoring.userAndRole.user.Users;

public class UserMapper {
    public static UserResponse toUserResponse(Users user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().getName())
                .status(user.getUserStatus())
                .lastLogin(user.getLastLogin())
                .enabled(user.isEnabled() ? "YES" : "NO")
                .addedBy(user.getAddedBy())
                .updatedBy(user.getUpdatedBy())
                .addedAt(user.getAddedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}

