package org.sfec.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Forensic crime project",
                version = "0.1",
                description = "Application for cumulating info about crimes",
                contact = @Contact(
                        name = "glebprokop@gmail.com",
                        email = "glebprokop@gmail.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://opensource.org/licenses/mit-license.php"
                )
        ),
        security = {
                @SecurityRequirement(name = "authToken")
        }
)
@SecuritySchemes(value = {
        @SecurityScheme(name = "authToken",
                type = SecuritySchemeType.APIKEY,
                in = SecuritySchemeIn.HEADER,
                paramName = "Authorization",
                description = "JWT Authentication token")
})
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
