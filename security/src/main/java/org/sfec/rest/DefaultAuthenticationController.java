package org.sfec.rest;

import lombok.RequiredArgsConstructor;
import org.sfec.jwt.JwtTokenProvider;
import org.sfec.user.JwtUser;
import org.sfec.util.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
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

    @PostMapping("login")
    public ResponseEntity login(@RequestBody DefaultRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            JwtUser user = service.findJwtUserByName(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.generateToken(user);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e){
            System.out.println("Bad credentials");
            e.printStackTrace();
//            throw new BadCredentialsException("Bad credentials");
        }

        Map<Object, Object> response2 = new HashMap<>();
        return new ResponseEntity(response2, HttpStatus.BAD_REQUEST);
    }
}
