package com.springproject.blogapp.security;

import com.springproject.blogapp.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    private JWTService jwtService;
    private UserService  userService;

    public AppSecurityConfig(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter() throws  Exception {
        return new JWTAuthenticationFilter(
                new JWTAuthenticationManager(jwtService, userService)
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JWTAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                // 1. Enable CORS and Disable CSRF using the modern lambda DSL
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/login", "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/articles", "/articles/**").permitAll()
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);

        return http.build();
    }
}
