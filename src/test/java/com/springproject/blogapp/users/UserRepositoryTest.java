package com.springproject.blogapp.users;

import com.springproject.blogapp.JpaTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import(JpaTestConfig.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void can_create_user() {
        var user = UserEntity.builder()
                .username("aastha")
                .email("aastha@gmail.com")
                .bio("this is my bio")
                .image("htttp.image.com")
                .build();

        userRepository.save(user);
    }

    @Test
    @Order(2)
    void can_find_user() {
        var user = UserEntity.builder()
                .username("aastha")
                .email("aastha@gmail.com")
                .bio("this is my bio")
                .image("htttp.image.com")
                .build();

        userRepository.save(user);

        var users=userRepository.findAll();
        Assertions.assertEquals(1,users.size());
    }

}
