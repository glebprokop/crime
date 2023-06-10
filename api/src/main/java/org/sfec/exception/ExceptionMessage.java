package org.sfec.exception;

import lombok.Builder;
import lombok.Data;

/**
 * Class used for generate custom error description with unique UUID id and
 * {@link ExceptionCodes} error status code
 */
@Data
@Builder
public class ExceptionMessage {

    private String id;

    private Integer code;

    private String message;
}