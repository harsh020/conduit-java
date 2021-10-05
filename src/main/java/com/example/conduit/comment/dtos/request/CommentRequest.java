package com.example.conduit.comment.dtos.request;

import com.example.conduit.comment.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CommentRequest {
    @JsonProperty
    private _Comment comment;

    @Getter
    public static class _Comment {
        @JsonProperty
        String body;
    }
}
