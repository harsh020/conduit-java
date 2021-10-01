package com.example.conduit.tag;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(String title, String description) {
        Tag tag = new Tag(title, description);

        return tagRepository.save(tag);
    }

    public Tag getTagByTitle(String title) {
        //TODO: raise if tag is null

        return tagRepository.findTagByTitle(title);
    }

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }
}
