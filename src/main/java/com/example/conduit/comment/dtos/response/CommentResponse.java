package com.example.conduit.comment.dtos.response;

import com.example.conduit.comment.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponse {
    @JsonProperty
    private final Comment comment;

    public static CommentResponse fromEntity(Comment c) {
        return new CommentResponse(c);
    }
}
