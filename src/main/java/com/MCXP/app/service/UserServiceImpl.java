package com.MCXP.app.service;

import com.MCXP.app.dto.request.UserRequest;
import com.MCXP.app.dto.response.UserResponse;
import com.MCXP.app.entity.Role;
import com.MCXP.app.entity.User;
import com.MCXP.app.mapper.UserMapper;
import com.MCXP.app.repository.RoleRepository;
import com.MCXP.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse createUser(UserRequest userRequest) {
        String roleName = userRequest.getRole();
        Role role = roleRepository.getRolesByName(userRequest.getRole())
                .orElseThrow(() ->
                        new RuntimeException("Role not found")
                );
        User user = userMapper.toEntity(userRequest, role);

        user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        System.out.println("After save");

        return userMapper.toResponse(savedUser);
    }

    @Override
  public List<UserResponse> getAllUsers(){
      List<User> users = userRepository.findAll();
      List<UserResponse> userResponseList = users.stream().map(user -> userMapper.toResponse(user)).toList();
      return userResponseList;
    }

    @Override
    public UserResponse getUserById(Long id) {


        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));


        UserResponse resp = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .status("SUCCESS")
                .role(user.getRole().getName())
                .build();


        return resp;
    }
}
