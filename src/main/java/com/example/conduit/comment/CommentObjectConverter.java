package com.example.conduit.comment;

import com.example.conduit.comment.dtos.response.CommentResponse;

public class CommentObjectConverter {
    public CommentResponse entityToResponse(Comment comment) {
        return CommentResponse.fromEntity(comment);
    }
}
