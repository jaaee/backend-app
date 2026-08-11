package com.MCXP.app.service;

import com.MCXP.app.dto.request.LoginRequest;
import com.MCXP.app.dto.response.LoginResponse;
import com.MCXP.app.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
   public  LoginResponse authenticateUser(LoginRequest loginRequest){

        Authentication authentication =
                authenticationManager.authenticate( new UsernamePasswordAuthenticationToken
                        (loginRequest.getUserName(),loginRequest.getPassword()));

        String token = jwtService.generateToken(authentication.getName());

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
