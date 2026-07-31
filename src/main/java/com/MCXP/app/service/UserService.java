package com.MCXP.app.service;

import com.MCXP.app.dto.response.UserResponse;
import com.MCXP.app.entity.User;

public interface UserService {

    UserResponse addUser(User user);
}
