package com.example.conduit.user;

import com.example.conduit.user.dtos.request.UserLoginRequest;
import com.example.conduit.user.dtos.request.UserSignupRequest;
import com.example.conduit.user.dtos.request.UserUpdateRequest;
import com.example.conduit.user.dtos.response.UserProfileResponse;
import com.example.conduit.user.dtos.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    UserService userService;
    UserObjectConverter converter;

    public UserController(UserService userService, UserObjectConverter userObjectConverter) {
        this.userService = userService;
        this.converter = userObjectConverter;
    }

    @PostMapping("/users")
    ResponseEntity<UserResponse> registerUser(@RequestBody UserSignupRequest body) {
        User user = userService.createNewUser(
                body.getUser().getUsername(),
                body.getUser().getPassword(),
                body.getUser().getEmail()
        );

        return new ResponseEntity<>(converter.entityToResponse(user), HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest body) {
        User user = userService.verifyUser(
                body.getUser().getEmail(),
                body.getUser().getPassword()
        );
        return ResponseEntity.ok(converter.entityToResponse(user));
    }

    @GetMapping("/user")
    ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(converter.entityToResponse(user));
    }

    @PatchMapping("/user")
    ResponseEntity<UserResponse> updateUser(@AuthenticationPrincipal User user,
                                            @RequestBody UserUpdateRequest body) {
        user = userService.updateUser(
                user,
                body.getUser().getUsername(),
                body.getUser().getPassword(),
                body.getUser().getEmail(),
                body.getUser().getBio(),
                body.getUser().getImage()
        );
        return new ResponseEntity<>(converter.entityToResponse(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/profiles/{username}")
    ResponseEntity<UserProfileResponse> getUserProfile(@AuthenticationPrincipal User user,
                                                       @PathVariable String username) {
        return ResponseEntity.ok(
                converter.entityToResponse(
                        user,
                        userService.getUserByUsername(username)
                )
        );
    }

    @PatchMapping("/profiles/{username}/follow")
    ResponseEntity<UserProfileResponse> followUser(@AuthenticationPrincipal User user,
                                                   @PathVariable String username) {
        user = userService.followUser(user, username);
        return ResponseEntity.ok(
                converter.entityToResponse(
                        user,
                        userService.getUserByUsername(username)
                )
        );
    }

    @DeleteMapping("/profile/{username}/follow")
    ResponseEntity<UserProfileResponse> unfollowUser(@AuthenticationPrincipal User user,
                                                     @PathVariable String username) {
        user = userService.followUser(user, username);
        return ResponseEntity.ok(
                converter.entityToResponse(
                        user,
                        userService.getUserByUsername(username)
                )
        );
    }
}
