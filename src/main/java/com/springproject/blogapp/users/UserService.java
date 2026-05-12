package com.springproject.blogapp.users;

import com.springproject.blogapp.users.dtos.createUserReqest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("User with username" + username + " not found");
        }

        public UserNotFoundException(Long id) {
            super("User with id" + id + " not found");
        }
    }

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(createUserReqest req){
        var newUser = UserEntity.builder()
                .username(req.getUsername())
        //        .password(password) #TODO: encrypt password
                .email(req.getEmail())
                .bio(req.getBio())
                .image(req.getImage())
                .build();

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
        // TODO: match password
        return user;
    }
}
