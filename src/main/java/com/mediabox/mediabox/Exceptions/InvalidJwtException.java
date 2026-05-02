package com.mediabox.mediabox.Exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidJwtException extends BadCredentialsException {

    public InvalidJwtException(String message) {
        super(message);
    }
}
