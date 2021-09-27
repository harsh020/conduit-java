package com.example.conduit.tag;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    Tag createTag(String title, String description) {
        Tag tag = new Tag(title, description);

        return tagRepository.save(tag);
    }

    Tag getTagByTitle(String title) {
        //TODO: raise if tag is null

        return tagRepository.findTagByTitle(title);
    }

    List<Tag> getTags() {
        return tagRepository.findAll();
    }
}
