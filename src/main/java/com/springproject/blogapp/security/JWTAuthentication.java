package com.springproject.blogapp.security;

import com.springproject.blogapp.users.UserEntity;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JWTAuthentication implements Authentication {
    String jwt;
    UserEntity userEntity;

    public JWTAuthentication(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Returns the credentials of {@code Authentication} request
     * for e.g. the password, the bearer token, the cookie
     * @return
     */
    @Override
    public @Nullable String getCredentials() {
        return jwt;
    }

    @Override
    public @Nullable Object getDetails() {
        return null;
    }

    /**
     * Returns the principal of {@code Authentication} request
     * The pricipal is the entity that is being authenticated
     * In this case it is user
     * @return
     */
    @Override
    public @Nullable UserEntity getPrincipal() {
        return userEntity;
    }

    @Override
    public boolean isAuthenticated() {
        return (userEntity != null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return "";
    }
}
