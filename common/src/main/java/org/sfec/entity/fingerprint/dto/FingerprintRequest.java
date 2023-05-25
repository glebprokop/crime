package org.sfec.entity.fingerprint.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseRequestDto;
import org.sfec.entity.crime.dto.CrimeRequest;
import org.sfec.entity.expert.dto.ExpertRequest;
import org.sfec.entity.image.dto.ImageRequest;

import java.sql.Timestamp;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class FingerprintRequest extends BaseRequestDto {

    @NotNull
    private String regNumber;

    @NotNull
    private Timestamp fingerprintFindDate;

    @NotNull
    private Long serialNumber;

    private ExpertRequest expert;

    private CrimeRequest crime;

    private ImageRequest image;
}