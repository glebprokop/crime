package org.sfec.service.identity;

import org.sfec.entity.identity.Identity;
import org.sfec.entity.identity.dto.IdentityRequest;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.identity.IdentityRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.TimeManager;
import org.springframework.stereotype.Service;

@Service
public class IdentityService extends AbstractService<Identity, IdentityRequest, IdentityRepository> {

    public IdentityService(CommonMapper<Identity, IdentityRequest> mapper,
                           IdentityRepository repository,
                           TimeManager timeManager) {
        super(mapper, repository, timeManager);
    }
}
