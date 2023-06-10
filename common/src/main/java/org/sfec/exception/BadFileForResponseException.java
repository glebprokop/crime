package org.sfec.exception;

/**
 * Custom analog for {@link java.io.FileNotFoundException} exception, used in runtime.
 * Use this exception if you don`t want throw such exception in the controllers
 */
public class BadFileForResponseException extends RuntimeException {

    public BadFileForResponseException() {
    }

    public BadFileForResponseException(String message) {
        super(message);
    }
}
