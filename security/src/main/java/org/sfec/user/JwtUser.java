package org.sfec.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.sfec.util.SecurityService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Custom {@link UserDetails} implementation to describe the security user in this module. So, you need to
 * describe the {@link SecurityService} class to load the user in database
 * and convert application user entity to this class
 */
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class JwtUser implements UserDetails {

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
