package com.eventbooking.events.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eventbooking.events.dtos.request.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class AppAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //1. extract the authentication credentials from the body of request

        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = request.getInputStream();
        AuthRequest authRequest = mapper.readValue(inputStream, AuthRequest.class);

        //2i. create an object of type Authentication that is not yet authenticated
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        //2ii. send the unauthenticated Authentication object to AuthenticationManager
       Authentication authenticationResult = authenticationManager.authenticate(authentication);

        boolean isAuthenticationSuccessful = authenticationResult.isAuthenticated();
        if (isAuthenticationSuccessful) {
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
           String accessToken = JWT.create()
                    .withIssuer("Shoppers_delight")
                    .withClaim(authRequest.getUsername(), new Date())
                    .withExpiresAt(Instant.now().plusSeconds(86400*7))
                    .sign(Algorithm.HMAC256("This is our secret key"));
           Map<String, String> authResponse = new HashMap<>();
           authResponse.put("access_token", accessToken);
        }



    }
}
