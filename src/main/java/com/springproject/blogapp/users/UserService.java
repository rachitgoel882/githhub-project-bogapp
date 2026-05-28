package com.springproject.blogapp.users;

import com.springproject.blogapp.users.dtos.createUserReqest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(createUserReqest req){
        var newUser = UserEntity.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword())) //#TODO: encrypt password
                .email(req.getEmail())
                //.bio(req.getBio())
                //.image(req.getImage())
                .build();

        //newUser.setPassword(passwordEncoder.encode(req.getPassword()));

        return userRepository.save(newUser);
    }

    public UserEntity getUser(String username) {
        var user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UserNotFoundException(username);
        }
        return user;
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserEntity loginUser(String username, String password) {
        var user = userRepository.findByUsername(username);
        if(user == null) {
            throw  new UserNotFoundException(username);
        }
        var passMatch = passwordEncoder.matches(password, user.getPassword());
        if(!passMatch) {
            throw new InvalidCredentialsException();
        }
        // TODO: match password
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("User with username " + username + " not found");
        }

        public UserNotFoundException(Long id) {
            super("User with id" + id + " not found");
        }
    }

    public static class InvalidCredentialsException extends IllegalArgumentException {
        public InvalidCredentialsException() {
            super("Invalid userId or password combination");
        }
    }
}
