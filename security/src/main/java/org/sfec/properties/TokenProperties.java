package org.sfec.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@ConfigurationProperties(prefix = "security.token")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TokenProperties {

    private String secret;

    private Long expiration;

    private LinkedHashMap<String, Object> headers;

    private String requestHeaderAuthorization = "Authorization";
}
