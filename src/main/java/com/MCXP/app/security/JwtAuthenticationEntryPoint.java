package com.MCXP.app.security;

import com.MCXP.app.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;




    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)

            throws IOException, ServletException {


        response.setContentType(
                "application/json"
        );


        response.setStatus(
                HttpServletResponse.SC_UNAUTHORIZED
        );


        ErrorResponse errorResponse =
                new ErrorResponse(
                        401,
                        "Authentication required or token expired",
                        LocalDateTime.now()
                );


        response.getWriter()
                .write(
                        objectMapper
                                .writeValueAsString(errorResponse)
                );
    }
}
