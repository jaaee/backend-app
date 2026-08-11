package com.MCXP.app.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import io.jsonwebtoken.JwtException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {


        // Step 1: Get Authorization header
        String authHeader = request.getHeader("Authorization");


        // No token present
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }


        try {
        // Step 2: Remove "Bearer "
        String token = authHeader.substring(7);



        // Step 3: Extract username from JWT
        String username = jwtService.extractUserName(token);



        // Step 4: Check if user is already authenticated
        if (username != null &&
                SecurityContextHolder.getContext()
                        .getAuthentication() == null) {



            // Step 5: Load user from database
            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(username);



            // Step 6: Validate token
            if (jwtService.isTokenValid(
                    token,
                    userDetails)) {



                // Step 7: Create authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );



                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );



                // Step 8: Store authentication in SecurityContext
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);


            }
        }

        } catch (ExpiredJwtException e) {

            SecurityContextHolder.clearContext();

        } catch (JwtException e) {

            SecurityContextHolder.clearContext();
        }



        // Step 9: Continue request
        filterChain.doFilter(request, response);
    }

}
