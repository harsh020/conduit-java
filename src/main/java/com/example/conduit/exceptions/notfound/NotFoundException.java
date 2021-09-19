package com.example.conduit.exceptions.notfound;

import com.example.conduit.exceptions.ConduitException;

public class NotFoundException extends ConduitException {
    public NotFoundException(String message) {
        super(message);
    }
}
