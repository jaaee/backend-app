package com.MCXP.app.mapper;

import com.MCXP.app.dto.request.UserRequest;
import com.MCXP.app.dto.response.UserResponse;
import com.MCXP.app.entity.Role;
import com.MCXP.app.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Component
public class UserMapper {
    public User toEntity(UserRequest userRequest, Role role){
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .userName((userRequest.getUserName()))
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .role(role).build();
    }
   public  UserResponse  toResponse (User user){
       return UserResponse.builder()
               .id(user.getId())
               .firstName(user.getFirstName())
               .lastName(user.getLastName())
               .userName(user.getUserName())
               .passwordHash(user.getPasswordHash())
               .email(user.getEmail())
               .phoneNumber(user.getPhoneNumber())
               .role(user.getRole().getName()).build();
   }
}
