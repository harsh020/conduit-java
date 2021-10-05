package com.example.conduit.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentByTitle(@Param("title") String title);
}
