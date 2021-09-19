package com.example.conduit.exceptions.invalid;

import com.example.conduit.exceptions.ConduitException;

public class InvalidException extends ConduitException {
    public InvalidException(String message) {
        super(message);
    }
}
