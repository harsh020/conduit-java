package com.example.conduit.user.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserLoginRequest {
    @JsonProperty
    private _User user;

    @Getter
    public class _User {
        @JsonProperty
        String email;

        @JsonProperty
        String password;
    }
}
