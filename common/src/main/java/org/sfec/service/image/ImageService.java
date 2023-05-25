package org.sfec.service.image;

import org.sfec.entity.image.Image;
import org.sfec.entity.image.dto.ImageRequest;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.image.ImageRepository;
import org.sfec.service.AbstractService;
import org.sfec.util.TimeManager;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends AbstractService<Image, ImageRequest, ImageRepository> {

    public ImageService(CommonMapper<Image, ImageRequest> mapper,
                        ImageRepository repository,
                        TimeManager timeManager) {
        super(mapper, repository, timeManager);
    }
}
