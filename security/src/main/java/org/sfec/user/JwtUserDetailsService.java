package org.sfec.user;

import org.sfec.util.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * Custom {@link UserDetailsService} class, used to load the converted {@link JwtUser} from the any datasource.
 * Application must to describe the {@link SecurityService} implementation to approve the loading users for
 * authentication
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

    /**
     * WARNING - BIG QUESTION IN IT
     */
    SecurityService<?> securityService;

    public JwtUserDetailsService(SecurityService<?> securityService) {
        this.securityService = securityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser jwtUser = securityService.findJwtUserByName(username);
                //orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found"));

        if (jwtUser == null){
            throw new UsernameNotFoundException("User not found");
        }

        return jwtUser;
    }
}
