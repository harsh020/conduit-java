package com.example.conduit.user.dtos.response;

import com.example.conduit.user.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    @JsonProperty
    private final _User user;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    public static class _User {
        @JsonProperty
        String username;

        @JsonProperty
        String email;

        @JsonProperty
        String token;

        @JsonProperty
        String bio;

        @JsonProperty
        String image;
    }

    public static UserResponse fromUserEntity(User user, String token) {
        return new UserResponse(
                new _User(
                        user.getProfile().getUsername(),
                        user.getEmail(),
                        token,
                        user.getProfile().getBio(),
                        user.getProfile().getImage()
                )
        );
    }
}
