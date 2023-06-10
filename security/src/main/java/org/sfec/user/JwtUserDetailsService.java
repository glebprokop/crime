package org.sfec.user;

import org.sfec.exception.BadCredentialsException;
import org.sfec.util.SecurityService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Custom {@link UserDetailsService} class, used to load the converted {@link JwtUser} from the any datasource.
 * Application must to describe the {@link SecurityService} implementation to approve the loading users for
 * authentication
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

    SecurityService<?> securityService;

    public JwtUserDetailsService(SecurityService<?> securityService) {
        this.securityService = securityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            return securityService.findJwtUserByName(username);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
