package org.sfec.configuration;

import org.sfec.properties.WebSecurityProperties;
import org.sfec.user.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Main configuration security class used for the configure the {@link SecurityFilterChain} filter chain.
 * For configuration in this class need yo use the {@link WebSecurityProperties} class, contains all security
 * properties for application.
 */
@Configuration
public class WebSecurityConfiguration {

    private final JwtUserDetailsService jwtUserDetailsService;

    private final WebSecurityConfigurator configurator;

    public WebSecurityConfiguration(JwtUserDetailsService jwtUserDetailsService,
                                    WebSecurityConfigurator configurator) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.configurator = configurator;
    }

    /**
     * Bean for {@link PasswordEncoder} object, used for code/decode the password in
     * application.
     *
     * @return {@link PasswordEncoder} object
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(6);
    }

    /**
     * Bean for {@link AuthenticationManager} object, used for organisation of all operations in
     * authentication (loading user from datasource, comparison of the client credentials with the
     * credentials of users in datasource, and so on). Default realisation is the
     * {@link DaoAuthenticationProvider} class, which use the database.
     *
     * @return {@link AuthenticationManager} object, {@link DaoAuthenticationProvider} in default
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(jwtUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        ProviderManager providerManager = new ProviderManager(provider);

        return providerManager;
    }

    /**
     * Main method to configure the {@link HttpSecurity} object. In this method you may to
     * change all params for configuration your web security based on the {@link SecurityFilterChain} <p>
     * - see {@link WebSecurityProperties} to understand how to configure properties for security <p>
     * - see {@link org.sfec.properties.TokenProperties} to to understand how to configure properties for
     * JWT token
     *
     * @param http {@link HttpSecurity} object (configured and getting from Spring Security)
     * @return {@link SecurityFilterChain} object, configured according the properties file
     * @throws Exception throwing by {@link HttpSecurity} class
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return configurator.setHttpSecurity(http)
                .configureDefaultProperties()
                .configureSessionCreationPolicy()
                .configureOpenMatchers()
                .configureLockMatchers()
                .configureJwtFilter()
                .buildSecurityFilterChain();
    }
}
