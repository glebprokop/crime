package org.sfec.properties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "security.web")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WebSecurityProperties {

    @NotNull
    private HashMap<String, List<String>> privateMatchers = new HashMap<>();

    private String[] publicMatchers = {};

    private String loginPage = "";

    private String logoutPage = "";

    private Boolean csrf = false;

    private String sessionCreationPolicy = "STATELESS";
}
