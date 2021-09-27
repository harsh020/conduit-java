package com.example.conduit.tag;

import com.example.conduit.tag.dtos.request.TagRequest;
import com.example.conduit.tag.dtos.response.TagResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {
    TagService tagService;
    TagObjectConverter converter;

    public TagController(TagService tagService, TagObjectConverter converter) {
        this.tagService = tagService;
        this.converter = converter;
    }

    @PostMapping("/tags")
    ResponseEntity<TagResponse> createTag(@RequestBody TagRequest details) {
        Tag tag = tagService.createTag(
                details.getTag().getTitle(),
                details.getTag().getDescription()
        );
        return new ResponseEntity<>(converter.entityToResponse(tag), HttpStatus.CREATED);
    }

    @GetMapping("/tags/{title}")
    ResponseEntity<TagResponse> getTag(@PathVariable String title) {
        Tag tag = tagService.getTagByTitle(title);

        return ResponseEntity.ok(converter.entityToResponse(tag));
    }

    @GetMapping("/tags")
    ResponseEntity<TagResponse> getTags() {
        List<Tag> tags = tagService.getTags();
        return ResponseEntity.ok(converter.entityToResponse(tags));
    }
}
