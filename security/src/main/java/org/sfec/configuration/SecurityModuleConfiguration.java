package org.sfec.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "org.sfec")
public class SecurityModuleConfiguration {
}
