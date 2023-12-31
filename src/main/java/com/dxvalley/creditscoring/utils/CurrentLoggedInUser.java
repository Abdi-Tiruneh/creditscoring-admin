package com.dxvalley.creditscoring.utils;

import com.dxvalley.creditscoring.exceptions.customExceptions.ResourceNotFoundException;
import com.dxvalley.creditscoring.exceptions.customExceptions.UnauthorizedException;
import com.dxvalley.creditscoring.userAndRole.user.UserRepository;
import com.dxvalley.creditscoring.userAndRole.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentLoggedInUser {

    private final UserRepository userRepository;

    public Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            throw new UnauthorizedException("Access denied. Please provide a valid authentication token.");

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Please login with your email and try again."));
        // If a user changes his or her email address, he or she must log in again.
    }
}