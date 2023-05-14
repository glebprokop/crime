package org.sfec.exception;

/**
 * Special org.sfec.common.exception used in validation of org.sfec.common.repository implemented classes.
 * We can throw this org.sfec.common.exception in case then our org.sfec.common.repository can`t find the org.sfec.common.entity in datasource
 *
 */
public class EntityNotFoundException extends RuntimeException{

    /**
     * Main constructor class used for throwing new org.sfec.common.exception.
     *
     * @param message String message used for info about org.sfec.common.exception
     * @param err the fundamental reason of org.sfec.common.exception
     */
    public EntityNotFoundException(String message, Throwable err) {
        super(message, err);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String paramName, Long paramValue){
        super("Entity not found with the param '" + paramName + "' where value is '" + paramValue + "'");
    }
}