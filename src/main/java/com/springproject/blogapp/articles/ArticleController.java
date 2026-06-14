package com.springproject.blogapp.articles;

import com.springproject.blogapp.users.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("")
    public String getAtricle(){
        return "article created";
    }

    @GetMapping("/{id}")
    public String getArticleById(@PathVariable("id") String id){
        return "article retrieved with id: " + id;
    }

    @PostMapping("")
    public String createArticle(@AuthenticationPrincipal UserEntity user){
        return "article created by user: " + user.getUsername();
    }
}
