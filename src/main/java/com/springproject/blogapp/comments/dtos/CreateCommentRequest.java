package com.springproject.blogapp.comments.dtos;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateCommentRequest {
    @Nullable
    private String title;
    @NonNull
    private String body;
}
