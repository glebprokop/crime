package org.sfec.controller.image;

import org.sfec.controller.CrudController;
import org.sfec.entity.image.dto.ImageRequest;
import org.sfec.service.image.ImageService;

public abstract class AbstractImageController implements CrudController<ImageRequest> {

    private final ImageService imageService;

    protected AbstractImageController(ImageService imageService) {
        this.imageService = imageService;
    }
}
