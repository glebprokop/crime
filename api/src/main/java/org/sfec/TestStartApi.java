package org.sfec;

import org.sfec.configuration.CommonModuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication()
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@EnableCaching
@PropertySource("classpath:datasource-properties.yml")
@Import({CommonModuleConfiguration.class})
public class TestStartApi {
    public static void main(String[] args) {
        SpringApplication.run(TestStartApi.class, args);
    }
}