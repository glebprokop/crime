package org.sfec.exception.handler;

import io.jsonwebtoken.JwtException;
import org.apache.log4j.Logger;
import org.sfec.exception.BadCredentialsException;
import org.sfec.exception.EntityNotFoundException;
import org.sfec.exception.ExceptionCodes;
import org.sfec.exception.ExceptionMessage;
import org.sfec.exception.ExceptionMessageFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.sfec.exception.ExceptionCodes.BAD_CREDENTIALS_ERROR;
import static org.sfec.exception.ExceptionCodes.BAD_TOKEN_ERROR;
import static org.sfec.exception.ExceptionCodes.DATA_NOT_FOUND_ERROR;
import static org.sfec.exception.ExceptionCodes.ERROR;
import static org.sfec.exception.ExceptionCodes.HTTP_BAD_PARAM_TYPE_ERROR;
import static org.sfec.exception.ExceptionCodes.HTTP_CLIENT_ERROR;

/**
 * Exception handler class using to work with exceptions thrown by methods of controllers. This class
 * "listen" all controller methods, catch exceptions and return the special {@link ResponseEntity} object
 * contains information about the exception <p>
 * see - {@link ExceptionCodes} class - enum contains unique application codes of errors <p>
 * see - {@link ExceptionMessage} class - class used for generating application error message (with uniq UUID id,
 * error code and exception message)
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger log = Logger.getLogger(DefaultExceptionHandler.class);

    private final ExceptionMessageFactory exceptionMessageFactory;

    public DefaultExceptionHandler(ExceptionMessageFactory exceptionMessageFactory) {
        this.exceptionMessageFactory = exceptionMessageFactory;
    }

    /**
     * Method for handling all non-specific exceptions. This method return the
     * "internal server error" http status (status code 500) in request
     *
     * @param e any general non-specific exception
     * @return {@link ResponseEntity} object contains the {@link ExceptionCodes} "ERROR" code
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionMessage> handleAnyGeneralException(Exception e) {
        ExceptionMessage exceptionMessage = exceptionMessageFactory
                .generateMessage(e, ERROR.getCode());
        log.error(exceptionMessage.getId() + " " + e.getMessage(), e);

        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method for handling exceptions type of "data not found in data source". This method return the
     * "internal server error" http status (status code 500) in request
     *
     * @param e {@link EntityNotFoundException} object usually thrown by repositories
     * @return {@link ResponseEntity} object contains the {@link ExceptionCodes} "DATA_NOT_FOUND_ERROR" code
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleEntityNotFoundException(EntityNotFoundException e) {
        ExceptionMessage exceptionMessage = exceptionMessageFactory
                .generateMessage(e, DATA_NOT_FOUND_ERROR.getCode());
        log.warn(exceptionMessage.getId() + " " + e.getMessage(), e);

        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method for handling non-specific exceptions of http client/requests (http status code 4**)
     *
     * @param e {@link HttpClientErrorException} object usually thrown during the incorrect client actions
     * @return {@link ResponseEntity} object contains the {@link ExceptionCodes} "HTTP_CLIENT_ERROR" code
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ExceptionMessage> handleBadRequestException(HttpClientErrorException e) {
        ExceptionMessage exceptionMessage = exceptionMessageFactory
                .generateMessage(e, HTTP_CLIENT_ERROR.getCode());
        log.error(exceptionMessage.getId() + " " + e.getMessage(), e);

        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    /**
     * Method for handling exceptions of http client/requests, when client use incorrect data type in
     * request (use String instead of Integer f.e.)
     *
     * @param e {@link MethodArgumentTypeMismatchException} object usually thrown during the incorrect request data
     * @return {@link ResponseEntity} object contains the {@link ExceptionCodes} "HTTP_BAD_PARAM_TYPE_ERROR"
     * code
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionMessage> handleBadRequestException(MethodArgumentTypeMismatchException e) {
        ExceptionMessage exceptionMessage = exceptionMessageFactory
                .generateMessage(e, HTTP_BAD_PARAM_TYPE_ERROR.getCode());
        log.error(exceptionMessage.getId() + " " + e.getMessage(), e);

        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionMessage> handleBadCredentialsException(BadCredentialsException e) {
        ExceptionMessage exceptionMessage = exceptionMessageFactory
                .generateMessage(e, BAD_CREDENTIALS_ERROR.getCode());
        log.error(exceptionMessage.getId() + " " + e.getMessage(), e);

        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionMessage> handleBadTokenException(JwtException e) {
        ExceptionMessage exceptionMessage = exceptionMessageFactory
                .generateMessage(e, BAD_TOKEN_ERROR.getCode());
        log.error(exceptionMessage.getId() + " " + e.getMessage(), e);

        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }
}