package com.springproject.blogapp.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Users")
@Setter
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String username;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(nullable = false)
    @NonNull
    private String email;

    //@Column(nullable = false)
    @Nullable
    private String bio;

    //@Column(nullable = false)
    @Nullable
    private String image;

}
