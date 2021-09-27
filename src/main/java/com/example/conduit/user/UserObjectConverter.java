package com.example.conduit.user;

import com.example.conduit.security.JWTService;
import com.example.conduit.user.dtos.response.UserProfileResponse;
import com.example.conduit.user.dtos.response.UserResponse;
import com.example.conduit.user.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserObjectConverter {
    JWTService jwtService;

    public UserObjectConverter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    public UserResponse entityToResponse(User user) {
        return UserResponse.fromUserEntity(user, jwtService.createJwt(user));
    }

    public UserProfileResponse entityToResponse(User user, User profile) {
        return UserProfileResponse.fromUserEntity(user, profile);
    }
}
