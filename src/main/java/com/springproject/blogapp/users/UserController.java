package com.springproject.blogapp.users;

import com.springproject.blogapp.common.dtos.ErrorResponse;
import com.springproject.blogapp.security.JWTService;
import com.springproject.blogapp.users.dtos.createUserReqest;
import com.springproject.blogapp.users.dtos.createUserResponse;
import com.springproject.blogapp.users.dtos.userLoginRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

    public UserController(UserService userService, ModelMapper modelMapper, JWTService jwtService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public ResponseEntity<createUserResponse> signupUser(@RequestBody createUserReqest response) {
        UserEntity savedUser = userService.createUser(response);

        var userResponse = modelMapper.map(savedUser, createUserResponse.class);
        userResponse.setToken(jwtService.createJWT(userResponse.getId()));

        URI savedUserURI = URI.create("/users/" + savedUser.getId());
        return ResponseEntity.created(savedUserURI)
                .body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<createUserResponse> loginUser(@RequestBody userLoginRequest response) {
        UserEntity savedUser = userService.loginUser(response.getUsername(), response.getPassword());

        var userResponse = modelMapper.map(savedUser, createUserResponse.class);
        userResponse.setToken(jwtService.createJWT(userResponse.getId()));

        return ResponseEntity.ok(userResponse);
    }

    @ExceptionHandler({
            UserService.UserNotFoundException.class,
            UserService.InvalidCredentialsException.class
    })
    ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex) {
        String message;
        HttpStatus status;

        if(ex instanceof UserService.UserNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UserService.InvalidCredentialsException) {
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;

        } else {
            message = "something went wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
