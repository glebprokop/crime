package org.sfec.util;

import org.sfec.user.JwtUser;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class used to link the main application module (or module responsible for the
 * access to the datasource and loading the user entity (or other entity associated as
 * user in application). Generic class where type T is the entity type for user.
 * Meanly this class described how application user loading from the datasource and
 * converting to the {@link JwtUser} class, used in this security module. So you
 * need to implement this interface in the application to use the web security
 *
 * @param <T> application entity type for user
 */
public interface SecurityService<T> {

    /**
     * Method to find any entity in datasource which play role as logged user in
     * application, and convert it in {@link JwtUser} object used in security module.
     * This method usually realized in services or DAO layer.
     *
     * @param name username for searching in datasource
     * @return {@link JwtUser} object loaded from datasource and converted
     * @throws AuthenticationException if user with username not found
     */
    JwtUser findJwtUserByName(String name) throws AuthenticationException;

    /**
     * Method to convert the application user object of type <T> to the
     * {@link JwtUser} object
     *
     * @param t application entity type for user
     * @return {@link JwtUser} object
     */
    JwtUser doJwtUser(T t);

    /**
     * Method to convert the {@link JwtUser} object to the {@link JwtUser} object
     *
     * @param jwtUser the {@link JwtUser} object
     * @return T - application entity type for user
     */
    T doApplicationUser(JwtUser jwtUser) throws AuthenticationException;

    /**
     * Default method converted user authorities (like "ROLE_USER", "ROLE_ADMIN") into
     * the {@link GrantedAuthority} object`s list, for using during authorization
     *
     * @param roles list of user roles
     * @return ready list of {@link GrantedAuthority} objects
     */
    default List<GrantedAuthority> generateAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        return authorities;
    }
}
