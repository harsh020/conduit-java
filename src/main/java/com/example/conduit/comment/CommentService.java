package com.example.conduit.comment;

import com.example.conduit.article.Article;
import com.example.conduit.article.ArticleRepository;
import com.example.conduit.article.exceptions.ArticleNotFoundException;
import com.example.conduit.user.models.User;

import java.util.List;

public class CommentService {
    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public Comment createComment(User user, String slug, String body) {
        Article article = articleRepository.findArticleBySlug(slug);
        if(article == null) {
            throw new ArticleNotFoundException("The article that you are trying to find does not exist.");
        }

        Comment comment = new Comment(body, user.getProfile(), article);
        List<Comment> comments = article.getComments();
        comments.add(comment);
        article.setComments(comments);
        articleRepository.save(article);

        return commentRepository.save(comment);
    }

    public Comment getComment(String slug, Long id) {
        Article article = articleRepository.findArticleBySlug(slug);
        if(article == null) {
            throw new ArticleNotFoundException("The article that you are trying to find does not exist.");
        }
        return getCommentFromArticle(slug, id);
    }

    public List<Comment> getComments(String slug) {
        Article article = articleRepository.findArticleBySlug(slug);
        if(article == null) {
            throw new ArticleNotFoundException("The article that you are trying to find does not exist.");
        }


        return article.getComments();
    }

    public Comment updateComment(String slug, Long id, String newBody) {
        Comment comment = getCommentFromArticle(slug, id);
        if(newBody != null) comment.setBody(newBody);

        return commentRepository.save(comment);
    }

    private Comment getCommentFromArticle(String slug, Long id) {
        Article article = articleRepository.findArticleBySlug(slug);

        Comment comment = null;
        List<Comment> comments = article.getComments();
        for(Comment c: comments) {
            if(id == c.getId()) {
                comment = c;
                break;
            }
        }
        return comment;
    }
}
