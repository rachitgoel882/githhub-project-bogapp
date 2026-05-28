package com.springproject.blogapp.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class JWTServiceTest {
    JWTService jwtService = new JWTService();

    @Test
    public void canCreateJWTFromUserId() {
        String Key = jwtService.createJWT(1002005L);

        assertNotNull(Key);
    }
}
