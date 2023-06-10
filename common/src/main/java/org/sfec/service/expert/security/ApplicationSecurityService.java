package org.sfec.service.expert.security;

import org.sfec.entity.common.EntityStatus;
import org.sfec.entity.expert.Expert;
import org.sfec.repository.expert.ExpertRepository;
import org.sfec.user.JwtUser;
import org.sfec.util.SecurityService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class add functionality for the correct security work, used for connection to
 * datasource and getting info about {@link Expert} object and convert it into
 * the {@link JwtUser} object, which is the image of user in security module
 */
@Service
@Primary
public class ApplicationSecurityService implements SecurityService<Expert> {

    private final ExpertRepository expertRepository;

    public ApplicationSecurityService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    @Override
    public JwtUser findJwtUserByName(String name) throws AuthenticationException {
        Optional<Expert> expert = expertRepository.findByUsername(name);

        return this.doJwtUser(expert.orElseThrow(() -> new UsernameNotFoundException("User not found with username " +
                name)));
    }

    @Override
    public JwtUser doJwtUser(Expert expert) {
        List<String> roles = new ArrayList<>();
        roles.add(expert.getExpertRole().getRoleName());

        JwtUser jwtUser = JwtUser.builder()
                .username(expert.getUsername())
                .password(expert.getPassword())
                .enabled(expert.getEntityStatus().equals(EntityStatus.ENABLE))
                .authorities(this.generateAuthorities(roles))
                .build();

        return jwtUser;
    }

    @Override
    public Expert doApplicationUser(JwtUser jwtUser) throws AuthenticationException {
        Optional<Expert> expert = expertRepository.findByUsername(jwtUser.getUsername());

        return expert.orElseThrow(() -> new UsernameNotFoundException("User not found with username " +
                jwtUser.getUsername()));
    }
}
