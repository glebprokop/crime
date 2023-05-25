package org.sfec.service.role;

import org.sfec.entity.role.ExpertRole;
import org.sfec.entity.role.dto.ExpertRoleRequest;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.role.ExpertRoleRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.TimeManager;
import org.springframework.stereotype.Service;

@Service
public class ExpertRoleService extends AbstractService<ExpertRole, ExpertRoleRequest,
        ExpertRoleRepository> {

    public ExpertRoleService(CommonMapper<ExpertRole, ExpertRoleRequest> mapper,
                             ExpertRoleRepository repository,
                             TimeManager timeManager) {
        super(mapper, repository, timeManager);
    }
}
