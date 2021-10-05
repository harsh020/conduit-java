package com.example.conduit.comment;

import com.example.conduit.comment.dtos.request.CommentRequest;
import com.example.conduit.comment.dtos.response.CommentResponse;
import com.example.conduit.user.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    CommentService commentService;
    CommentObjectConverter converter;

    @PostMapping("/articles/{slug}/comments")
    ResponseEntity<CommentResponse> createComment(@AuthenticationPrincipal User user,
                                                  @PathVariable String slug,
                                                  @RequestBody CommentRequest detail) {
        Comment comment = commentService.createComment(user, slug, detail.getComment().getBody());
        return new ResponseEntity<>(converter.entityToResponse(comment), HttpStatus.CREATED);
    }

    @GetMapping("/articles/{slug}/comments/{id}")
    public ResponseEntity<CommentResponse> getComment(@AuthenticationPrincipal User user,
                                                      @PathVariable String slug,
                                                      @PathVariable Long id) {
        Comment comment = commentService.getComment(slug, id);
        return ResponseEntity.ok(converter.entityToResponse(comment));
    }

    @GetMapping("/articles/{slug}/comments")
    public String getComments(@AuthenticationPrincipal User user, @PathVariable String slug) {
        //TODO
        return "Getting all comments for article " + slug;
    }

    @PutMapping("/articles/{slug}/comments/{id}")
    public  ResponseEntity<Comment> updateComment(@AuthenticationPrincipal User user,
                                                  @PathVariable String slug,
                                                  @PathVariable Long id,
                                                  @RequestBody CommentRequest details) {
        Comment comment = commentService.updateComment(slug, id, details.getComment().getBody());
        return ResponseEntity.ok(comment);
    }
}
