package org.sfec.exception;

import org.sfec.util.uuid.UUIDGenerator;
import org.springframework.stereotype.Component;

/**
 * Simple factory for generating the custom {@link ExceptionMessage} object, used in
 * the {@link org.sfec.exception.handler.DefaultExceptionHandler} class
 */
@Component
public class ExceptionMessageFactory {

    private final UUIDGenerator uuidGenerator;

    public ExceptionMessageFactory(UUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }

    /**
     * Factory method to generate the {@link ExceptionMessage} object
     *
     * @param code code of exception described in the {@link ExceptionCodes} class
     * @param e    {@link Throwable} object the reason of exception
     * @return configured {@link ExceptionMessage} object having uniq UUID id
     */
    public ExceptionMessage generateMessage(Throwable e, Integer code) {
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .id(uuidGenerator.generateUUID())
                .message(e.getMessage())
                .code(code)
                .build();

        return exceptionMessage;
    }
}
