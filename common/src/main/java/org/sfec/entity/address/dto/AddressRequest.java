package org.sfec.entity.address.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseRequestDto;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressRequest extends BaseRequestDto {

    @NotNull
    @Size(min = 1, max = 100)
    private String region;

    @NotNull
    @Size(min = 1, max = 100)
    private String district;

    @NotNull
    @Size(min = 1, max = 100)
    private String locality;

    private String building;

    private String corps;

    private String apartment;

    private String comment;

    @NotNull
    @Size(min = 1, max = 100)
    private String policeDepartment;
}
