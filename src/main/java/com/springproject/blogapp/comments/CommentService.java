package com.springproject.blogapp.comments;

import com.springproject.blogapp.articles.ArticleService;
import com.springproject.blogapp.comments.dtos.CreateCommentRequest;
import com.springproject.blogapp.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentEntity createComment(CreateCommentRequest req, Long articleId, Long userId) {
        var article = articleService.getArticleById(articleId);
        var user = userService.getUserById(userId);

        var comment = CommentEntity.builder()
                .title(req.getTitle())
                .body(req.getBody())
                .article(article)
                .author(user)
                .build();

        return commentRepository.save(comment);
    }

    public CommentEntity getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));
    }

    public static class CommentNotFoundException extends IllegalArgumentException {
        public CommentNotFoundException(Long id) {
            super("comment with id" + id + "not found");
        }
    }
}
