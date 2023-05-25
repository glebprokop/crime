package org.sfec.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.common.EntityStatus;

import java.sql.Timestamp;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class BaseRequestDto {

    private Long id;

    @NotNull
    private EntityStatus entityStatus;

    private Timestamp created;

    private Timestamp changed;
}
