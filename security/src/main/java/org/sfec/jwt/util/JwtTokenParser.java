package org.sfec.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.sfec.properties.TokenProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Util class used for parse tokens
 */
@Component
public class JwtTokenParser {

    private TokenProperties tokenProperties;

    public JwtTokenParser(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    /**
     * Method to generate expiration date based on the current date
     *
     * @return {@link Date} object contains expiration date
     */
    public Date generateExpirationDate() {
        Date expirationDate = new Date(this.generateCurrentDate().getTime() + tokenProperties.getExpiration());

        return expirationDate;
    }

    /**
     * Util method generating the current time according to the contract of
     * {@link Date} class constructor
     *
     * @return {@link Date} object contains current date
     */
    public Date generateCurrentDate() {
        return new Date();
    }

    /**
     * @param token
     * @return
     */
    public String getUsername(String token) {
        return Jwts.parser().
                setSigningKey(tokenProperties.getSecret()).
                parseClaimsJws(token).
                getBody().
                getSubject();
    }

    /**
     * Method for getting the expiration date from token
     *
     * @param token parsed token
     * @return {@link Date} object contains expiration date
     */
    public Date getExpirationDate(String token) {
        return this.getClaims(token).getExpiration();
    }

    /**
     * Method for getting the {@link Claims} from token
     *
     * @param token parsed token
     * @return {@link Claims} object contains token info
     */
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(tokenProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
