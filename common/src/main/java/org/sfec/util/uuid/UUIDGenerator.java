package org.sfec.util.uuid;

import java.util.UUID;

/**
 * Interface for UUID generators class. Has default realisation of getting the UUID
 */
public interface UUIDGenerator {

    /**
     * Method for generating the UUID
     *
     * @return string contains the uniq UUID
     */
    default String generateUUID(){
        return UUID.randomUUID().toString();
    }
}