package org.sfec;

import org.sfec.configuration.CommonModuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication()
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@Import({CommonModuleConfiguration.class})

public class TestStartApi {
    public static void main(String[] args) {
        SpringApplication.run(TestStartApi.class, args);


    }
}