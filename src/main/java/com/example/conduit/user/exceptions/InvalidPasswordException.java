package com.example.conduit.user.exceptions;

import com.example.conduit.exceptions.invalid.InvalidException;

public class InvalidPasswordException extends InvalidException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
