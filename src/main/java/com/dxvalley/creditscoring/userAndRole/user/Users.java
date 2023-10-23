package com.dxvalley.creditscoring.userManager.user;

import com.dxvalley.creditscoring.userManager.role.Role;
import com.dxvalley.creditscoring.utils.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String lastLogin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status userStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @Column(updatable = false)
    private String addedAt;

    private String updatedAt;

    @Column(nullable = false)
    private String addedBy;

    private String updatedBy;

    private boolean isEnabled;

    private boolean isDeleted;

    private String deletedAt;

    private String deleteBy;

    @PrePersist
    protected void onCreate() {
        addedAt = LocalDateTime.now().toString();
        updatedAt = addedAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role.getName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
