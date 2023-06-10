package org.sfec.configuration;

import jakarta.servlet.MultipartConfigElement;
import org.sfec.properties.ImageProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiModuleConfiguration {

    private final ImageProperties imageProperties;

    public ApiModuleConfiguration(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    /**
     * Bean used for loading images as files from the http requests.
     * See {@link org.sfec.controller.image.ImageController} looking for info about work with http.
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(imageProperties.getSizeMaxBytes());
        factory.setMaxRequestSize(imageProperties.getSizeMaxBytes());

        return factory.createMultipartConfig();
    }
}
