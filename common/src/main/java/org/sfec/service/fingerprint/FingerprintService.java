package org.sfec.service.fingerprint;

import org.sfec.entity.fingerprint.Fingerprint;
import org.sfec.entity.fingerprint.dto.FingerprintRequest;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.fingerprint.FingerprintRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.TimeManager;
import org.springframework.stereotype.Service;

@Service
public class FingerprintService extends AbstractService<Fingerprint, FingerprintRequest, FingerprintRepository> {

    public FingerprintService(CommonMapper<Fingerprint, FingerprintRequest> mapper,
                              FingerprintRepository repository,
                              TimeManager timeManager) {
        super(mapper, repository, timeManager);
    }
}