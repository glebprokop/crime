package org.sfec.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

/**
 * Property class contain info about images as files. See {@link org.sfec.entity.image.Image} if
 * you looking for image as entity.
 */
@Component
@ConfigurationProperties(prefix = "image")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ImageProperties {

    private Long sizeMin;

    private Long sizeMax;

    private String folder;

    public DataSize getSizeMinBytes() {
        return DataSize.ofBytes(sizeMin);
    }

    public DataSize getSizeMaxBytes() {
        return DataSize.ofBytes(sizeMax);
    }
}
