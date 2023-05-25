package org.sfec.service.department;

import org.sfec.entity.department.ExpertDepartment;
import org.sfec.entity.department.dto.ExpertDepartmentRequest;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.department.ExpertDepartmentRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.TimeManager;
import org.springframework.stereotype.Service;

@Service
public class ExpertDepartmentService extends AbstractService<ExpertDepartment, ExpertDepartmentRequest,
        ExpertDepartmentRepository> {

    public ExpertDepartmentService(CommonMapper<ExpertDepartment, ExpertDepartmentRequest> mapper,
                                   ExpertDepartmentRepository repository,
                                   TimeManager timeManager) {
        super(mapper, repository, timeManager);
    }
}
