package org.sfec.entity.crime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseRequestDto;

import java.sql.Timestamp;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class CrimeRequestForEntity extends BaseRequestDto {

    private Long policeRegNumber;

    private Long caseNumber;

    private String description;

    private Long criminalCodeArticle;

    private Timestamp crimeDate;

    private String crimeStatus;
}
