package com.springproject.blogapp.users.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class userLoginRequest {
    @NonNull
    String username;
    @NonNull
    String password;
}
