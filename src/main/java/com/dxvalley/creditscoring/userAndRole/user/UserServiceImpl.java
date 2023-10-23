package com.dxvalley.creditscoring.userAndRole.user;

import com.dxvalley.creditscoring.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.dxvalley.creditscoring.userAndRole.role.Role;
import com.dxvalley.creditscoring.userAndRole.role.RoleService;
import com.dxvalley.creditscoring.userAndRole.user.dto.*;
import com.dxvalley.creditscoring.utils.ApiResponse;
import com.dxvalley.creditscoring.utils.CurrentLoggedInUser;
import com.dxvalley.creditscoring.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserUtils userUtils;
    private final CurrentLoggedInUser currentLoggedInUser;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(UserRegistrationReq userReq) {
        if (userUtils.isEmailTaken(userReq.getEmail()))
            throw new ResourceAlreadyExistsException("Email is already taken");

        if (userUtils.isPhoneNumberTaken(userReq.getPhoneNumber()))
            throw new ResourceAlreadyExistsException("Phone number is already taken");

        Role role = roleService.getRoleByName("ADMIN");
        Users loggedInUser = currentLoggedInUser.getUser();
        Users user = userUtils.createUser(userReq, loggedInUser, role);
        Users savedUser = userRepository.save(user);

        return UserMapper.toUserResponse(savedUser);
    }

    @Override
    @Transactional
    public UserResponse editUser(UserUpdateReq updateReq) {
        Users user = currentLoggedInUser.getUser();

        if (updateReq.getFullName() != null) {
            user.setFullName(updateReq.getFullName());
        }

        // Update email if provided and different from the current email
        if (updateReq.getEmail() != null && !user.getUsername().equalsIgnoreCase(updateReq.getEmail())) {
            // Check if the new email is already taken
            if (userUtils.isEmailTaken(updateReq.getEmail())) {
                throw new ResourceAlreadyExistsException("Email is already taken");
            }
            user.setUsername(updateReq.getEmail());
        }

        // Update phone number if provided and different from the current phone number
        if (updateReq.getPhoneNumber() != null && !user.getPhoneNumber().equals(updateReq.getPhoneNumber())) {
            // Check if the new phone number is already taken
            if (userUtils.isPhoneNumberTaken(updateReq.getPhoneNumber())) {
                throw new ResourceAlreadyExistsException("Phone number is already taken");
            }
            user.setPhoneNumber(updateReq.getPhoneNumber());
        }

        user.setUpdatedBy(user.getFullName());
        Users savedUser = userRepository.save(user);

        return UserMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse me() {
        Users user = currentLoggedInUser.getUser();
        return UserMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getById(Long userId) {
        Users user = userUtils.getById(userId);
        return UserMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getUsers() {
        List<Users> users = userRepository.findAll(Sort.by(Sort.Order.asc("id")));
        return users.stream().map(UserMapper::toUserResponse).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> changePassword(ChangePassword temp) {
        Users user = currentLoggedInUser.getUser();

        userUtils.validateOldPassword(user, temp.getOldPassword());

        user.setPassword(passwordEncoder.encode(temp.getNewPassword()));
        user.setUpdatedBy(user.getFullName());

        userRepository.save(user);

        return ApiResponse.success("Password Changed Successfully!");
    }

    @Override
    public UserResponse blockUser(Long id) {
        Users user = userUtils.getById(id);

        user.setUserStatus(Status.BLOCKED);
        user.setUpdatedBy(currentLoggedInUser.getUser().getFullName());

        Users savedUser = userRepository.save(user);
        return UserMapper.toUserResponse(savedUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> delete(Long id) {
        Users user = userUtils.getById(id);

        // Set user attributes for deletion
        user.setDeleted(true);
        user.setDeletedAt(LocalDateTime.now().toString());
        user.setDeleteBy(currentLoggedInUser.getUser().getFullName());

        userRepository.save(user);
        return ApiResponse.success("Password Changed Successfully!");
    }

}
