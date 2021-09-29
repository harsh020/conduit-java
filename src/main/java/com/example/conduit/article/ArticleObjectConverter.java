package com.example.conduit.article;

import com.example.conduit.article.dtos.response.ArticleResponse;
import com.example.conduit.security.JWTAuthenticationFilter;
import com.example.conduit.user.models.User;

public class ArticleObjectConverter {
    public ArticleResponse entityToResponse(User user, Article article) {
        return ArticleResponse.fromEntity(user, article);
    }

}
