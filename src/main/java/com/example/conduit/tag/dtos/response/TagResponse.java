package com.example.conduit.tag.dtos.response;

import com.example.conduit.tag.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TagResponse {
    @JsonProperty
    private final List<String> tags;

    public TagResponse(ArrayList<String> tags) {
        this.tags = tags;
    }

    public static TagResponse fromTagEntity(Tag tag) {
        return new TagResponse(
                new ArrayList<String>(Arrays.asList(tag.getTitle()))
        );
    }

    public static TagResponse fromTagEntity(List<Tag> tags) {
        List<String> tagTitle = new ArrayList<>();
        for (Tag t: tags) {
            tagTitle.add(t.getTitle());
        }

        return new TagResponse(tagTitle);
    }
}
