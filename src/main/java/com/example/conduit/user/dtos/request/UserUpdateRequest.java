package com.example.conduit.user.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserUpdateRequest {
    @JsonProperty
    private _User user;

    @Getter
    public static class _User {
        @JsonProperty
        private String username;

        @JsonProperty
        private String password;

        @JsonProperty
        private String email;

        @JsonProperty
        private String bio;

        @JsonProperty
        private String image;
    }
}
