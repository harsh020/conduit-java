package com.example.conduit.article;

import com.example.conduit.article.dtos.request.ArticleRequest;
import com.example.conduit.article.dtos.response.ArticleResponse;
import com.example.conduit.user.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArticleController {
    ArticleService articleService;
    ArticleObjectConverter converter;

    @PostMapping("/articles")
    ResponseEntity<ArticleResponse> createArticle(@AuthenticationPrincipal User user,
                                                  @RequestBody ArticleRequest details) {
        Article article =  articleService.createArticle(
                details.getArticle().getTitle(),
                details.getArticle().getDescription(),
                details.getArticle().getBody(),
                user,
                details.getArticle().getTagList()
        );
        return new ResponseEntity<>(converter.entityToResponse(user, article), HttpStatus.CREATED);
    }

    @GetMapping("/articles/{slug}")
    ResponseEntity<ArticleResponse> getArticles(@AuthenticationPrincipal User user, @PathVariable String slug) {
        Article article = articleService.getArticlesBySlug(slug);
        return ResponseEntity.ok(converter.entityToResponse(user, article));
    }

    @GetMapping("/articles")
    String getArticles(@AuthenticationPrincipal User user,
                       @RequestParam("author") Optional<String> author,
                       @RequestParam("tag") Optional<String> tag,
                       @RequestParam("favorited") Optional<String> favorited) {
        return "get all articles";
    }

    @PutMapping("/articles/{slug}")
    ResponseEntity<ArticleResponse> updateArticle(@AuthenticationPrincipal User author,
                                                  @PathVariable String slug,
                                                  @RequestBody ArticleRequest newArticle) {
        Article article = articleService.updateArticle(
                slug,
                newArticle.getArticle().getTitle(),
                newArticle.getArticle().getDescription(),
                newArticle.getArticle().getBody(),
                newArticle.getArticle().getTagList()
        );

        return new ResponseEntity<>(converter.entityToResponse(author, article), HttpStatus.CREATED);
    }

    @DeleteMapping("/article/{slug}")
    String deleteArticle(@AuthenticationPrincipal User author, @PathVariable String slug) {
        return "deleting article " + slug;
    }
}
