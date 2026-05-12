package com.springproject.blogapp.articles;

import com.springproject.blogapp.articles.dtos.CreateArticleRequest;
import com.springproject.blogapp.articles.dtos.UpdateArticleRequest;
import com.springproject.blogapp.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ArticleService {
    public static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug) {
            super("slug" + slug + " not found");
        }

        public ArticleNotFoundException(Long id) {
            super("Article with id" + id + " not found");
        }
    }

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserService userService;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Iterable<ArticleEntity> findAllArticles() {
        return articleRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug) {
        var article= articleRepository.findBySlug(slug);
        if (article == null) {
            throw  new ArticleNotFoundException(slug);
        }
        return article;
    }

    public ArticleEntity getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    public ArticleEntity createArticle(CreateArticleRequest art, Long userId) {
        var author = userService.getUserById(userId);
        var article = ArticleEntity.builder()
                .title(art.getTitle())
                .body(art.getBody())
                // TODO: create a proper slugification function
                .slug(art.getTitle().toLowerCase().replaceAll("\\s+","-"))
                .author(author)
                .subtitle(art.getSubtitle())
                .build();

        return articleRepository.save(article);
    }

    public ArticleEntity updateArticle(UpdateArticleRequest article, Long userId) {
        var updateAtricle = articleRepository.findById(userId).orElseThrow(() -> new ArticleNotFoundException(userId));
        if(article.getTitle() != null){
            updateAtricle.setTitle(article.getTitle());
            updateAtricle.setSlug(article.getTitle().toLowerCase().replaceAll("\\s+","-"));
        }
        if(article.getBody() != null){
            updateAtricle.setBody(article.getBody());
        }

        if(article.getSubtitle() != null){
            updateAtricle.setSubtitle(article.getSubtitle());
        }
        return articleRepository.save(updateAtricle);
    }
}
