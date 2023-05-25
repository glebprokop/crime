package org.sfec.configuration;

import org.sfec.jwt.JwtTokenFilter;
import org.sfec.properties.WebSecurityProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class contains methods to configure parameters of {@link HttpSecurity} object during forming
 * the {@link SecurityFilterChain} chain of the security filters
 */
@Component
public class WebSecurityConfigurator {

    /**
     * Configurable object, not autowired (use setHttpSecurity to set object in this class for configure)
     */
    private HttpSecurity httpSecurity;

    private final WebSecurityProperties properties;

    private final JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfigurator(WebSecurityProperties properties,
                                   JwtTokenFilter jwtTokenFilter) {
        this.properties = properties;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    public HttpSecurity getHttpSecurity() {
        return this.httpSecurity;
    }

    public WebSecurityConfigurator setHttpSecurity(HttpSecurity httpSecurity) {
        this.httpSecurity = httpSecurity;

        return this;
    }

    /**
     * Method to configure the {@link HttpSecurity} object to lock the unauthorised access to pages. <p>
     * - see {@link WebSecurityProperties} to understand how to configure properties for security
     *
     * @return {@link WebSecurityConfigurator} object configured
     * @throws Exception throwing by {@link HttpSecurity} class
     */
    public WebSecurityConfigurator configureLockMatchers() throws Exception {
        String page;

        for (String p : properties.getPrivateMatchers().keySet()) {
            page = p;
            String[] roles = properties.getPrivateMatchers().get(page);

            this.httpSecurity.authorizeHttpRequests().
                    requestMatchers(page)
                    .hasAnyRole(roles);
        }
        this.httpSecurity.authorizeHttpRequests()
             .anyRequest()
             .authenticated();

        return this;
    }

    /**
     * Method to configure the {@link HttpSecurity} object to lock the unauthorised access to pages. <p>
     * - see {@link WebSecurityProperties} to understand how to configure properties for security
     *
     * @return {@link WebSecurityConfigurator} object configured
     * @throws Exception throwing by {@link HttpSecurity} class
     */
    public WebSecurityConfigurator configureOpenMatchers() throws Exception {
        String page;

        for (String s : properties.getPublicMatchers()) {
            page = s;

            this.httpSecurity.authorizeHttpRequests().
                    requestMatchers(page).permitAll();
            }

        return this;
    }

    /**
     * Method to configure the {@link HttpSecurity} object to set {@link JwtTokenFilter} filter <p>
     * - see {@link WebSecurityProperties} to understand how to configure properties for security
     *
     * @return {@link WebSecurityConfigurator} object configured
     */
    public WebSecurityConfigurator configureJwtFilter() {
        this.httpSecurity.addFilterBefore(this.jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return this;
    }

    /**
     * Method to configure the {@link HttpSecurity} object to set {@link SessionCreationPolicy} policy. <p>
     * - see {@link WebSecurityProperties} to understand how to configure properties for security
     *
     * @return {@link WebSecurityConfigurator} object configured
     * @throws Exception throwing by {@link HttpSecurity} class
     */
    public WebSecurityConfigurator configureSessionCreationPolicy() throws Exception {
        this.httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.valueOf(properties.getSessionCreationPolicy()));

        return this;
    }

    /**
     * Method to build {@link SecurityFilterChain} object configured before, based on the {@link HttpSecurity} object.
     *
     * @return configured {@link SecurityFilterChain} object
     * @throws Exception throwing by
     */
    public SecurityFilterChain buildSecurityFilterChain() throws Exception {
        return this.httpSecurity.build();
    }

    public WebSecurityConfigurator configureDefaultProperties() throws Exception {
        this.httpSecurity.httpBasic().disable()
                .csrf().disable();

        return this;
    }
}
