package org.sfec.entity.expert.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseRequestDto;
import org.sfec.entity.department.dto.ExpertDepartmentRequest;
import org.sfec.entity.role.dto.ExpertRoleRequest;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class ExpertRequest extends BaseRequestDto {

    private String email;

    @NotNull
    private String username;

    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String secondName;

    @NotNull
    private String lastName;

    @NotNull
    private ExpertRoleRequest expertRole;

    private ExpertDepartmentRequest expertDepartment;
}
