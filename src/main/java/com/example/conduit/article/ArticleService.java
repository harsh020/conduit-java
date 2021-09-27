package com.example.conduit.article;

import com.example.conduit.tag.Tag;
import com.example.conduit.user.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticleService {
    ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(String title, String description, String body,
                                 User author, List<Tag> tags) {
        Article article = new Article(
                title,
                description,
                body,
                author.getProfile(),
                (Set<Tag>) tags
        );
        return articleRepository.save(article);
    }

    public List<Article> getArticles
}
