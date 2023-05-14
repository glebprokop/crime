package org.sfec.configuration;

import org.sfec.properties.WebSecurityProperties;
import org.sfec.user.JwtUserDetailsService;
import org.sfec.util.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@ComponentScan(basePackages = "org.sfec")
//@Profile("security")
public class SecurityModuleConfiguration {

//    private WebSecurityProperties properties;
//
////    private JwtTokenFilter jwtTokenFilter;
//
//    private JwtUserDetailsService jwtUserDetailsService;
//
//    public SecurityModuleConfiguration(WebSecurityProperties properties,
//                                       //JwtTokenFilter jwtTokenFilter,
//                                       JwtUserDetailsService jwtUserDetailsService) {
//        this.properties = properties;
////        this.jwtTokenFilter = jwtTokenFilter;
//        this.jwtUserDetailsService = jwtUserDetailsService;
//    }
//
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(jwtUserDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//
//        ProviderManager providerManager = new ProviderManager(provider);
//
//        return providerManager;
//    }
//
//
//    /**
//     * Main method to configure the {@link HttpSecurity} object. In this method you may to
//     * change all params for configuration your web security based on the {@link SecurityFilterChain}
//     *
//     * @param http {@link HttpSecurity} object
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/auth/login").permitAll();
////                .requestMatchers("/api/v1/**").hasRole("USER")
////                .anyRequest().authenticated();
//
//        return http.build();
//
//        //http.authorizeHttpRequests().requestMatchers("api/auth/login").permitAll();
////        http = this.configureJwtFilter(http);
////        http = this.configureLockMatchers(http);
////
////
////        return http.build();
//    }
//
//    /**
//     * Method to configure the {@link HttpSecurity} object to lock the unauthorised access to pages
//     *
//     * @param http {@link HttpSecurity} object
//     * @return {@link HttpSecurity} object after configuration
//     */
//    public HttpSecurity configureLockMatchers(HttpSecurity http) throws Exception {
//        String role;
//
//        for (String s : properties.getPrivateMatchers().keySet()) {
//            role = s;
//            String[] pages = properties.getPrivateMatchers().get(role);
//
//            for (String page : pages) {
//                http.authorizeHttpRequests().
//                        requestMatchers(page).
//                        hasRole(role);
//            }
//        }
//
//        http.authorizeHttpRequests().anyRequest().authenticated();
//
//        return http;
//    }
//






}
