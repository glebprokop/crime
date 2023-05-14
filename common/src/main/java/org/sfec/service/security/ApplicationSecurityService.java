package org.sfec.service.security;

import org.sfec.entity.EntityStatus;
import org.sfec.entity.Expert;
import org.sfec.repository.expert.ExpertRepository;
import org.sfec.user.JwtUser;
import org.sfec.util.SecurityService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ApplicationSecurityService implements SecurityService<Expert> {

    ExpertRepository expertRepository;

    public ApplicationSecurityService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    @Override
    public JwtUser findJwtUserByName(String name) {
        Expert expert = expertRepository.findByUsername(name);

        return this.doJwtUser(expert);
    }

    @Override
    public JwtUser doJwtUser(Expert expert) {
        List<String> roles = new ArrayList<>();
        roles.add(expert.getExpertRole().getRoleName());

        JwtUser jwtUser = JwtUser.builder()
                .username(expert.getUsername())
                .password(expert.getPassword())
                .enabled(expert.getStatus().equals(EntityStatus.ENABLE))
                .authorities(this.generateAuthorities(roles))
                .build();

        return jwtUser;
    }

    @Override
    public Expert doApplicationUser(JwtUser jwtUser) {
        Expert expert = Expert.builder()
                .username(jwtUser.getUsername())
                .password(jwtUser.getPassword())
                .build();

        return expert;
    }
}
