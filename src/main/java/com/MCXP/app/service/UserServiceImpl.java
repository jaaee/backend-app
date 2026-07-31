package com.MCXP.app.service;

import com.MCXP.app.dto.response.UserResponse;
import com.MCXP.app.entity.User;
import com.MCXP.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;

    @Override
    UserResponse addUser(User user){
       userRepository.save(user);
    }
}
