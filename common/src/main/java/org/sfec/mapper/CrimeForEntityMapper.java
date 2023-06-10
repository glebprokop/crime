package org.sfec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.sfec.entity.crime.Crime;
import org.sfec.entity.crime.dto.CrimeRequestForEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface CrimeForEntityMapper extends CommonMapper<Crime, CrimeRequestForEntity> {
}
