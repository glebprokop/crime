package org.sfec.exception;

/**
 * Class contains application statuses for handling errors
 */
public enum ExceptionCodes {
    /**
     * Info status for any event
     */
    INFO(10),

    /**
     * Warning status for any event
     */
    WARNING(20),

    /**
     * Error status for non-specific errors
     */
    ERROR(30),

    /**
     * Error status for any non-specific server error
     */
    SERVER_ERROR(31),

    /**
     * Error status for any error in data source access time
     */
    DATA_ACCESS_ERROR(311),

    /**
     * Error status when data not found in data source
     */
    DATA_NOT_FOUND_ERROR(312),

    /**
     * Error status for any non-specified http-client fatal error (application crashed)
     */
    HTTP_CLIENT_ERROR(40),

    /**
     * Error status for incorrect credentials (login or password)
     */
    BAD_CREDENTIALS_ERROR(41),

    /**
     * Error status for incorrect token (unsigned or expired)
     */
    BAD_TOKEN_ERROR(411),


    /**
     * Error status for incorrect type of request param data
     */
    HTTP_BAD_PARAM_TYPE_ERROR(60);

    /**
     * Special exception code used for identifying of exception type. See
     * descriptions of status codes for this application before
     */
    private Integer code;

    ExceptionCodes(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}