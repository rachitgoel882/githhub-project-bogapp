package com.springproject.blogapp.users.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

@Data
public class createUserResponse {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String image;
    private String token;
}
