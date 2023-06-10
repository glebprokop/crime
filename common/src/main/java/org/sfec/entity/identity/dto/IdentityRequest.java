package org.sfec.entity.identity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseRequestDto;
import org.sfec.entity.fingerprint.dto.FingerprintRequest;

import java.sql.Timestamp;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class IdentityRequest extends BaseRequestDto {

    private Timestamp identityDate;

    private String personFirstName;

    private String personSecondName;

    private String personLastName;

    private Timestamp personBirthDate;

    private String comment;

    private FingerprintRequest fingerprint;
}
