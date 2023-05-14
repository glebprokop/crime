package org.sfec.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "security.web")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WebSecurityProperties {

    private Map<String, String[]> privateMatchers;

    //private Map<String, String[]> publicMatchers;

    private String loginPage;

    private String logoutPage;
}
