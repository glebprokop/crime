package org.sfec.exception;

/**
 * Special exception used in validation of repository classes.
 * We can throw this exception in case then our repository can`t find the entity in datasource
 *
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Main constructor class used for throwing new exception.
     *
     * @param message String message used for info about exception
     * @param err the fundamental reason of exception
     */
    public EntityNotFoundException(String message, Throwable err) {
        super(message, err);
    }

    /**
     * Common constructor using the message for description of exception
     *
     * @param message String message used for info about exception
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Custom constructor used for generating the exception based on the name of entity parameter not founded
     * in datasource, and value of this parameter
     *
     * @param paramName name of parameter
     * @param paramValue value of parameter
     */
    public EntityNotFoundException(String paramName, Object paramValue){
        super("Entity not found with the param '" + paramName + "' where value is '" + paramValue + "'");
    }
}