package com.springproject.blogapp.security;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class JWTAuthenticationConvertor implements AuthenticationConverter {
    @Override
    public @Nullable Authentication convert(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        var token = authHeader.substring(7);
        return new JWTAuthentication(token);
    }
}
