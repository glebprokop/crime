package org.sfec.rest;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.sfec.exception.BadCredentialsException;
import org.sfec.jwt.JwtTokenProvider;
import org.sfec.user.JwtUser;
import org.sfec.util.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/auth")
public class DefaultAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final SecurityService service;

    public DefaultAuthenticationController(AuthenticationManager authenticationManager,
                                           JwtTokenProvider jwtTokenProvider,
                                           SecurityService service) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody DefaultRequestDto requestDto) {
        Map<Object, Object> response = new HashMap<>();

        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    requestDto.getPassword()));

            JwtUser user = service.findJwtUserByName(username);
            String token = jwtTokenProvider.generateToken(user);
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (JwtException e) {
            throw new BadCredentialsException("JWT token invalid");
        }
    }
}
