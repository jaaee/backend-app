package com.MCXP.app.security;

import com.MCXP.app.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;




    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)

            throws IOException, ServletException {


        response.setContentType(
                "application/json"
        );


        response.setStatus(
                HttpServletResponse.SC_FORBIDDEN
        );


        ErrorResponse errorResponse =
                new ErrorResponse(
                        403,
                        "You do not have permission to access this resource",
                        LocalDateTime.now()
                );


        response.getWriter()
                .write(
                        objectMapper
                                .writeValueAsString(errorResponse)
                );
    }
}
