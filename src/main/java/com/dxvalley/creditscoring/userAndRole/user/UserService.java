package com.dxvalley.creditscoring.userAndRole.user;

import com.dxvalley.creditscoring.userAndRole.user.dto.ChangePassword;
import com.dxvalley.creditscoring.userAndRole.user.dto.UserRegistrationReq;
import com.dxvalley.creditscoring.userAndRole.user.dto.UserResponse;
import com.dxvalley.creditscoring.userAndRole.user.dto.UserUpdateReq;
import com.dxvalley.creditscoring.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponse register(UserRegistrationReq userReq);

    UserResponse me();

    UserResponse getById(Long userId);

    List<UserResponse> getUsers();

    UserResponse blockUser(Long id);

    UserResponse editUser(UserUpdateReq updateReq);

    ResponseEntity<ApiResponse> changePassword(ChangePassword temp);

    ResponseEntity<ApiResponse> delete(Long id);
}
