package org.sfec.exception;

/**
 * Special declared exception used for wrap the {@link NoSuchFieldException} exception.
 */
public class FieldEditException extends RuntimeException {

    public FieldEditException(String message) {
        super(message);
    }
}
