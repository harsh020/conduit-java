package com.example.conduit.user.dtos.response;

import com.example.conduit.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileResponse {
    @JsonProperty
    private final _Profile profile;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    public static class _Profile {
        @JsonProperty
        String username;

        @JsonProperty
        String bio;

        @JsonProperty
        String image;

        @JsonProperty
        Boolean following;
    }

    public static UserProfileResponse fromUserEntity(User user, User profile) {
        Boolean following = Boolean.FALSE;
//        if(user != null) {
//            following = user.getFollowing().contains(profile);
//        }
        return new UserProfileResponse(
                new _Profile(
                        profile.getUsername(),
                        profile.getBio(),
                        profile.getImage(),
                        following
                )
        );
    }
}
