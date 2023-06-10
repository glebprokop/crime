package org.sfec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.sfec.entity.role.ExpertRole;
import org.sfec.entity.role.dto.ExpertRoleRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpertRoleMapper extends CommonMapper<ExpertRole, ExpertRoleRequest> {
}
