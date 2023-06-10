package org.sfec.service.expert;

import org.sfec.entity.department.ExpertDepartment;
import org.sfec.entity.expert.Expert;
import org.sfec.entity.expert.dto.ExpertRequest;
import org.sfec.entity.role.ExpertRole;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.expert.ExpertRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.FieldEncoder;
import org.sfec.util.TimeManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertService extends AbstractService<Expert, ExpertRequest, ExpertRepository> {

    private final FieldEncoder encoder;

    public ExpertService(CommonMapper<Expert, ExpertRequest> mapper,
                         ExpertRepository repository,
                         TimeManager timeManager,
                         FieldEncoder encoder) {
        super(mapper, repository, timeManager);
        this.encoder = encoder;
    }

    public ExpertRequest createWithPasswordEncode(ExpertRequest expertRequest) {
        ExpertRequest passwordEncodedExpert = encoder.encodeField(expertRequest, "password");

        return create(passwordEncodedExpert);
    }

    public ExpertRequest updateWithPasswordEncode(ExpertRequest expertRequest) {
        ExpertRequest passwordEncodedExpert = encoder.encodeField(expertRequest, "password");

        return update(passwordEncodedExpert);
    }


    public List<ExpertRequest> findAllByExpertRole(ExpertRole expertRole) {
        List<Expert> objects = this.getRepository().findAllByExpertRole(expertRole);

        return this.getMapper().toTransfer(objects);
    }

    public List<ExpertRequest> findAllByExpertDepartment(ExpertDepartment expertDepartment) {
        List<Expert> objects = this.getRepository().findAllByExpertDepartment(expertDepartment);

        return this.getMapper().toTransfer(objects);
    }
}
