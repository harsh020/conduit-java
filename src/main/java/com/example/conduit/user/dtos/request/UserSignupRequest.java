package com.example.conduit.user.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
public class UserSignupRequest {
    @JsonProperty
    private _User user;

    @Getter
    public class _User {
        @JsonProperty
        private String username;

        @JsonProperty
        private String password;

        @JsonProperty
        private String email;
    }
}
