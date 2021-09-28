package com.example.conduit.article;

import com.example.conduit.user.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @PostMapping("/articles")
    String createArticle(@RequestBody String details) {
        return "created article";
    }

    @GetMapping("/articles/{slug}")
    String getArticles(@PathVariable String slug) {
        return "get article " + slug;
    }

    @GetMapping("/articles")
    String getArticles(@RequestParam("author") Optional<String> author,
                       @RequestParam("tag") Optional<String> tag,
                       @RequestParam("favorited") Optional<String> favorited) {
        return "get all articles";
    }

    @PutMapping("/articles/{slug}")
    String updateArticle(@AuthenticationPrincipal User author,
                         @PathVariable String slug,
                         @RequestBody String article) {
        return "update entire article " + slug + " by author " + author;
    }

    @DeleteMapping("/article/{slug}")
    String deleteArticle(@AuthenticationPrincipal User author, @PathVariable String slug) {
        return "deleting article " + slug;
    }
}
