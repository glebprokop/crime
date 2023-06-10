package org.sfec.exception;

/**
 * Custom runtime exception used for handling the IOException during reading the files
 * from requests in controllers
 */
public class BadFileInRequestException extends RuntimeException {

    public BadFileInRequestException(String message) {
        super(message);
    }
}
