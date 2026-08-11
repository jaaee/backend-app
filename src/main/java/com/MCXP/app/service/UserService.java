package com.MCXP.app.service;

import com.MCXP.app.dto.request.UserRequest;
import com.MCXP.app.dto.response.UserResponse;
import com.MCXP.app.entity.User;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest user);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

}
