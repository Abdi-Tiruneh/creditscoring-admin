//
//package com.dxvalley.creditscoring.controllers;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//import com.dxvalley.creditscoring.userManager.role.RoleRepository;
//import com.dxvalley.creditscoring.userManager.user.UserRepository;
//import com.dxvalley.creditscoring.userManager.user.Users;
//import org.springframework.http.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import com.dxvalley.creditscoring.models.response.ApiResponse;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import lombok.*;
//
//@RestController@RequiredArgsConstructor
//@RequestMapping("/auth/v1")
//public class AuthController {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    // Register User
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Users users) {
//
//        if (userRepository.findByUsername(users.getUsername()) != null) {
//            ApiResponse<?> response = new ApiResponse<>(409, "User already exists!", null);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        ResponseEntity<UserInfoResponse> res;
//        try {
//            String uri = "http://10.1.245.150:7081/equb/userInfo";
//            RestTemplate restTemplate = new RestTemplate();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            String requestBody = "{\"phoneNumber\":" + users.getUsername() + "}";
//
//            HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
//
//            res = restTemplate.exchange(uri, HttpMethod.POST, request, UserInfoResponse.class);
//
//            UserInfo userInfo = res.getBody().getUserInfo();
//            users.setFullName(userInfo.getFullName());
//            users.setEmail(userInfo.getEmail());
//            users.setIsEnabled(true);
//            users.setCreatedAt(LocalDateTime.now().toString());
//        } catch (Exception e) {
//            ApiResponse<?> response = new ApiResponse<>(500, "Can't find User with this phone Number on CBS!", null);
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//
//        List<Role> roles = new ArrayList<Role>(1);
//
//        roles.add(this.roleRepository.findByRoleName("admin"));
//
//        users.setRoles(roles);
//
//        users.setPassword(passwordEncoder.encode(users.getPassword()));
//
//        return new ResponseEntity<>(userRepository.save(users), HttpStatus.CREATED);
//    }
//
//}
//
//@Data
//class MobileNumber {
//  private String phoneNumber;
//}
//
//
//@Data
//class UserInfoResponse {
//   private UserInfo userInfo;
//}
//
//@Data
//class UserInfo {
//   private String fullName;
//   private String gender;
//   private String dateOfBirth;
//   private String email;
//   private String imageUrl;
//   private String languageCode;
//   ArrayList<Object> accounts = new ArrayList<Object>();
//   @JsonProperty("Status")
//   private String Status;
//}
//
//@Data
//class Account{
//  private String isMain;
//  private String accountName;
//}
//
