package com.MCXP.app.controller;

import com.MCXP.app.dto.request.LoginRequest;
import com.MCXP.app.dto.response.LoginResponse;
import com.MCXP.app.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authenticationService.authenticateUser(loginRequest);
         return ResponseEntity.ok(loginResponse);
    }
}
