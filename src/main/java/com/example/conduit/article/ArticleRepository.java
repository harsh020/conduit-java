package com.example.conduit.article;

import com.example.conduit.tag.Tag;
import com.example.conduit.user.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findArticleBySlug(@Param("slug") String slug);
    List<Article> findArticlesByTitle(@Param("title") String title);
    List<Article> findArticlesByAuthor(@Param("author") UserProfile author);
    List<Article> findArticlesByTag(@Param("tag") Tag tag);
    List<Article> findArticlesByTags(@Param("tags") List<Tag> tags);
    List<Article> findArticlesByFavorited(@Param("favorited") UserProfile favorited);
}
