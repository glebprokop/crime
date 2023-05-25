package org.sfec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.sfec.entity.fingerprint.Fingerprint;
import org.sfec.entity.fingerprint.dto.FingerprintRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface FingerprintMapper extends CommonMapper<Fingerprint, FingerprintRequest>{
}
