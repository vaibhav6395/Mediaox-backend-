package com.mediabox.mediabox.Exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class AuthenticationFailedException extends BadCredentialsException {

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
