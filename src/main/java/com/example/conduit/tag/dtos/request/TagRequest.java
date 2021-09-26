package com.example.conduit.tag.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TagRequest {
    @JsonProperty
    private _Tag tag;

    @Getter
    public class _Tag {
        @JsonProperty
        String title;

        @JsonProperty
        String description;
    }
}
