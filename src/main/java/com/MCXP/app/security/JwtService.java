package com.MCXP.app.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String username);
    String extractUserName(String token);
    Boolean isTokenValid(String token, UserDetails userDetails );
    Boolean isTokenExpired(String token);
    Claims extractAllClaims(String token);
}
