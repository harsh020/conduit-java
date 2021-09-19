package com.example.conduit.security;

import com.example.conduit.user.User;
import com.example.conduit.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class JWTAuthManager implements AuthenticationManager {
    private final JWTService jwtService;
    private final UserService userService;

    public JWTAuthManager(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(!(authentication instanceof JWTAuthenticationFilter.JWTAuthentication)) {
            throw new IllegalStateException("This Authentication Manager deals only with JWT Authentication");
        }

        JWTAuthenticationFilter.JWTAuthentication jwtAuth = (JWTAuthenticationFilter.JWTAuthentication) authentication;
        String username = jwtService.decodeJwt(jwtAuth.getCredentials());
        User user = userService.getUserByUsername(username);
        jwtAuth.setUser(user);
        return jwtAuth;
    }
}
