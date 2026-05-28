package com.springproject.blogapp.articles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("")
    public String createArticle(){
        return "article created";
    }
}
