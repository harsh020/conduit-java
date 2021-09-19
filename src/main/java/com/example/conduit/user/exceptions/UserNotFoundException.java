package com.example.conduit.user.exceptions;

import com.example.conduit.exceptions.notfound.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
