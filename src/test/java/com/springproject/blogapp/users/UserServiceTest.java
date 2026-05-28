package com.springproject.blogapp.users;

import com.springproject.blogapp.JpaTestConfig;
import com.springproject.blogapp.users.dtos.createUserReqest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(JpaTestConfig.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        var newUser = userService.createUser(new createUserReqest(
                "rachit",
                "pass123",
                "rachit@gmail.com"
                //"My name is rachit",
                //"http.rac.com"
        ));
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals(newUser.getUsername(), "rachit");
    }
}
