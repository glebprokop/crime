package org.sfec.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.sfec.jwt.util.JwtTokenParser;
import org.sfec.properties.TokenProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This class used to work with JWT tokens. Class tasks: <p>
 * - generate JWT token based on user data (username) and creating date,
 * with the HS512 signature algorithm, include all token elements (header and claims); <p>
 * - validate JWT token according its expiration date
 */
@Component
public class JwtTokenProvider {

    private TokenProperties tokenProperties;

    private UserDetailsService userDetailsService;

    private final JwtTokenParser jwtTokenParser;

    public JwtTokenProvider(TokenProperties tokenProperties,
                            UserDetailsService userDetailsService,
                            JwtTokenParser jwtTokenParser) {
        this.tokenProperties = tokenProperties;
        this.userDetailsService = userDetailsService;
        this.jwtTokenParser = jwtTokenParser;
    }

    /**
     * Method for generating
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        Date currentDate = jwtTokenParser.generateCurrentDate();
        Date expirationDate = jwtTokenParser.generateExpirationDate();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret())
                .compact();
    }

    /**
     * Method to extract token from the http request. See {@link TokenProperties} class for
     * wide configure header of http which contain the token
     *
     * @param request {@link HttpServletRequest} object - mirror of http in Java
     * @return extracted token
     * @throws BadCredentialsException if token is null
     */
    public String resolveToken(HttpServletRequest request) throws BadCredentialsException {
        String token = request.getHeader(tokenProperties.getRequestHeaderAuthorization());

        return token;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(jwtTokenParser.getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Check for the token expiration date has not finished
     *
     * @param token for checking
     * @return boolean value of token expired
     */
    public Boolean isTokenExpired(String token) {
        Date expiration = jwtTokenParser.getExpirationDate(token);

        return expiration.before(jwtTokenParser.generateCurrentDate());
    }
}
