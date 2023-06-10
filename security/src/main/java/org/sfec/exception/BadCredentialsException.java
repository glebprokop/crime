package org.sfec.exception;

/**
 * Simple wrap exception for {@link org.springframework.security.core.AuthenticationException} exception,
 * used in {@link org.sfec.rest.DefaultAuthenticationController}
 */
public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String message) {
        super(message);
    }
}
