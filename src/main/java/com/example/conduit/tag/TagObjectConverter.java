package com.example.conduit.tag;

import com.example.conduit.tag.dtos.response.TagResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagObjectConverter {
    public TagResponse entityToResponse(Tag tag) {
        return TagResponse.fromTagEntity(tag);
    }

    public TagResponse entityToResponse(List<Tag> tags) {
        return TagResponse.fromTagEntity(tags);
    }
}
