package com.springproject.blogapp.articles;

import com.springproject.blogapp.users.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "Articles")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@ToString
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @Column(unique = true)
    private String slug;

    @Nullable
    private String subtitle;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;


    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;
}
