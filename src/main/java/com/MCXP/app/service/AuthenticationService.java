package com.MCXP.app.service;

import com.MCXP.app.dto.request.LoginRequest;
import com.MCXP.app.dto.response.LoginResponse;


public interface AuthenticationService {

   LoginResponse authenticateUser(LoginRequest loginRequest);
}
