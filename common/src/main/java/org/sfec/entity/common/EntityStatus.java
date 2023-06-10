package org.sfec.entity.common;

/**
 * Model enum class contains entity status in datasource. It used for "soft" and "hard" delete
 * model realization, then deleted object may be assign as "disable" or "deleted"
 */
public enum EntityStatus {
    ENABLE,
    DISABLE,
    DELETED;
}
