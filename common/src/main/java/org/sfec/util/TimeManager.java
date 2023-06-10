package org.sfec.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Util class allow to generate the current time and manage other time operations
 */
@Component
public class TimeManager {

    public static Timestamp currentTimeTwo() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * Generate current time using the default {@link Date} constructor
     *
     * @return {@link Timestamp} object contains current time of server
     */
    public Timestamp currentTime() {
        return new Timestamp(new Date().getTime());
    }
}
