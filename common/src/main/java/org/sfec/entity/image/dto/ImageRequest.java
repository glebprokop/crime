package org.sfec.entity.image.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseRequestDto;
import org.sfec.entity.fingerprint.dto.FingerprintRequest;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class ImageRequest extends BaseRequestDto {

    private String imageName;

    private Boolean containsImage;

    @NotNull
    private FingerprintRequest fingerprint;
}
