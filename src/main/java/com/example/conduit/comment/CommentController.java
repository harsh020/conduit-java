package com.example.conduit.comment;

import com.example.conduit.comment.dtos.request.CommentRequest;
import com.example.conduit.user.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @PostMapping("/articles/{slug}/comments")
    public String createComment(@AuthenticationPrincipal User user,
                                @PathVariable String slug,
                                @RequestBody CommentRequest commentDetail) {
        return "Create new comment";
    }

    @GetMapping("/articles/{slug}/comments/{id}")
    public String getComment(@AuthenticationPrincipal User user,
                             @PathVariable String slug,
                             @PathVariable Integer id) {
        return "Getting single comment";
    }

    @GetMapping("/articles/{slug}/comments")
    public String getComments(@AuthenticationPrincipal User user, @PathVariable String slug) {
        return "Getting all comments for article " + slug;
    }

    @PutMapping("/articles/{slug}/comments/{id}")
    public  String updateComment(@AuthenticationPrincipal User user,
                                 @PathVariable String slug,
                                 @PathVariable Integer id) {
        return "Update comment";
    }
}
