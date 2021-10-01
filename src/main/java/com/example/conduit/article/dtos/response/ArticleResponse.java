package com.example.conduit.article.dtos.response;

import com.example.conduit.article.Article;
import com.example.conduit.security.AppSecurityConfig;
import com.example.conduit.security.JWTAuthenticationFilter;
import com.example.conduit.tag.Tag;
import com.example.conduit.user.models.User;
import com.example.conduit.user.models.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.Hibernate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleResponse {
    @JsonProperty
    private _Article article;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    private static class _Article {
        @JsonProperty
        String slug;

        @JsonProperty
        String title;

        @JsonProperty
        String description;

        @JsonProperty
        String body;

        @JsonProperty
        List<String> tagList;

        @JsonProperty
        Date createdAt;

        @JsonProperty
        Date updatedAt;

        @JsonProperty
        Boolean favorited;

        @JsonProperty
        Integer favoritesCount;

        @JsonProperty
        UserProfile author;
    }

    //TODO: The author fiels is not in accordance with the spi spec, fix it
    public static ArticleResponse fromEntity(User user, Article article) {
        List<Tag> tags = (List<Tag>) article.getTags();

        List<String> tagList = new ArrayList<>();

        UserProfile userProfile = user.getProfile();

        UserProfile author = article.getAuthor();

        Boolean hasFavorited = false;
        if(article.getFavorited() != null) {
            hasFavorited = article.getFavorited()
                    .contains(userProfile);
        }


        tags.stream()
                .map(tag -> tagList.add(tag.getTitle()));

        ArticleResponse a = new ArticleResponse(
                new _Article(
                        article.getSlug(),
                        article.getTitle(),
                        article.getDescription(),
                        article.getBody(),
                        tagList,
                        article.getCreated(),
                        article.getUpdated(),
                        hasFavorited,
                        (article.getFavorited()==null?0:article.getFavorited().size()),
                        author
                )
        );
        System.out.println("***hellp = " + ", article = " + a);
        return a;
    }
}
