package com.example.conduit.comment;

import com.example.conduit.article.Article;
import com.example.conduit.article.ArticleRepository;
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
        //TODO: Check if article exists if not raise exception
        Article article = articleRepository.findArticleBySlug(slug);

        Comment comment = new Comment(body, user.getProfile(), article);
        List<Comment> comments = article.getComments();
        comments.add(comment);
        article.setComments(comments);
        articleRepository.save(article);

        return commentRepository.save(comment);
    }

    public Comment getComment(String slug, Long id) {
        //TODO: Check if article exists if not raise exception
        return getCommentFromArticle(slug, id);
    }

    public List<Comment> getComments(String slug) {
        //TODO: Check if article exists if not raise exception
        Article article = articleRepository.findArticleBySlug(slug);

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
