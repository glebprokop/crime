package org.sfec.configuration;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.sfec.properties.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    private final CacheProperties cacheProperties;

    public CacheConfiguration(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(cacheProperties.getCaches());
        cacheManager.setCaffeine(cacheProperties());
        return cacheManager;
    }

    public Caffeine<Object, Object> cacheProperties() {
        return Caffeine.newBuilder()
                .initialCapacity(cacheProperties.getInitialCapacity())
                .maximumSize(cacheProperties.getMaxSize())
                .expireAfterAccess(cacheProperties.getExpireInSeconds(), TimeUnit.SECONDS)
                .weakKeys()
                .recordStats();
    }
}
