package com.example.conduit.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findArticlesBySlug(@Param("slug") String slug);
    List<Article> findArticlesByTitle(@Param("title") String title);
    List<Article> findArticlesByAuthor(@Param("author") String author);
    List<Article> findArticlesByTag(@Param("tag") String tag);
    List<Article> findArticlesByTags(@Param("tags") List<String> tags);
    List<Article> findArticlesByFavorited(@Param("favorited") String favorited);
}
