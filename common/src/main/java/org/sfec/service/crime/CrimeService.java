package org.sfec.service.crime;

import jakarta.transaction.Transactional;
import org.sfec.entity.crime.Crime;
import org.sfec.entity.crime.dto.CrimeRequest;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.crime.CrimeRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.TimeManager;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CrimeService extends AbstractService<Crime, CrimeRequest, CrimeRepository> {

    private final CrimeRepository crimeRepository;

    public CrimeService(CommonMapper<Crime, CrimeRequest> mapper,
                        CrimeRepository repository,
                        TimeManager timeManager) {
        super(mapper, repository, timeManager);
        this.crimeRepository = repository;
    }

    @Transactional
    public void deleteBeforeDate(Timestamp date) {
        crimeRepository.deleteBeforeDate(date);
    }
}
