package org.sfec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.sfec.entity.identity.Identity;
import org.sfec.entity.identity.dto.IdentityRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface IdentityMapper extends CommonMapper<Identity, IdentityRequest> {
}
