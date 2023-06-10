package org.sfec.service.image;

import jakarta.transaction.Transactional;
import org.sfec.entity.image.Image;
import org.sfec.entity.image.dto.ImageRequest;
import org.sfec.mapper.CommonMapper;
import org.sfec.properties.ImageProperties;
import org.sfec.repository.image.ImageRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.TimeManager;
import org.sfec.util.UtilFileService;
import org.sfec.util.uuid.UUIDGenerator;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends AbstractService<Image, ImageRequest, ImageRepository> {

    private final ImageProperties imageProperties;

    private final UtilFileService utilFileService;

    private final UUIDGenerator uuidGenerator;

    public ImageService(CommonMapper<Image, ImageRequest> mapper,
                        ImageRepository repository,
                        TimeManager timeManager,
                        ImageProperties imageProperties,
                        UtilFileService utilFileService,
                        UUIDGenerator uuidGenerator) {
        super(mapper, repository, timeManager);
        this.imageProperties = imageProperties;
        this.utilFileService = utilFileService;
        this.uuidGenerator = uuidGenerator;
    }

    @Transactional
    public ImageRequest createWithUuid(ImageRequest imageRequest) {
        String uuid = uuidGenerator.generateUUID();
        imageRequest.setImageName(uuid);

        return create(imageRequest);
    }

    @Transactional
    public ImageRequest updateWithUuid(ImageRequest imageRequest) {
        String uuid = findOne(imageRequest.getId()).getImageName();
        imageRequest.setImageName(uuid);

        return update(imageRequest);
    }
}