package org.sfec.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "org.sfec")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAutoConfiguration
@EnableCaching
public class CommonModuleConfiguration {
}
