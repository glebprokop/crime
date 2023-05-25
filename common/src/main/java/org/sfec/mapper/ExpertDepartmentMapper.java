package org.sfec.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.sfec.entity.department.ExpertDepartment;
import org.sfec.entity.department.dto.ExpertDepartmentRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpertDepartmentMapper extends CommonMapper<ExpertDepartment, ExpertDepartmentRequest> {
}
