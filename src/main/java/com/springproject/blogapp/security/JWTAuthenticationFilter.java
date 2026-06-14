package com.springproject.blogapp.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JWTAuthenticationFilter extends AuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(JWTAuthenticationManager jwtAuthenticationManager) {
        super(jwtAuthenticationManager, new JWTAuthenticationConvertor());

        this.setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }
}
