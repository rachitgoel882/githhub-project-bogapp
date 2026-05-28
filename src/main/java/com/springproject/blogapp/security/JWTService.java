package com.springproject.blogapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static final String JWT_Key = "ffhkyiryrsf767ghgmnv4d8bf1gsbfdndh26154mdgnmjkjmjkli8lmb";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_Key);

    public String createJWT(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                //.withExpiresAt() TODO: setup expiry paramter
                .sign(algorithm);
    }

    public Long retriveUserId(String token){
        var decodedJWT = JWT.decode(token);
        var user = Long.valueOf(decodedJWT.getSubject());
        return user;
    }
}
