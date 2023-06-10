package org.sfec.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {

    private String[] caches;

    private Integer initialCapacity;

    private Integer maxSize;

    private Integer expireInSeconds;
}
