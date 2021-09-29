package com.example.conduit.article.dtos.request;

import com.example.conduit.tag.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleRequest {
    @JsonProperty
    private _Article article;

    @Getter
    public static class _Article {
        @JsonProperty
        String title;

        @JsonProperty
        String description;

        @JsonProperty
        String body;

        @JsonProperty
        List<String> tagList;
    }
}
