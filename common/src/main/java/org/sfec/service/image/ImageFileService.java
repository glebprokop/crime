package org.sfec.service.image;

import jakarta.transaction.Transactional;
import org.sfec.entity.image.dto.ImageRequest;
import org.sfec.exception.BadFileForResponseException;
import org.sfec.exception.BadFileInRequestException;
import org.sfec.properties.ImageProperties;
import org.sfec.util.FileExtension;
import org.sfec.util.UtilFileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageFileService {

    private final ImageProperties imageProperties;

    private final UtilFileService utilFileService;

    private final ImageService imageService;

    public ImageFileService(ImageProperties imageProperties,
                            UtilFileService utilFileService,
                            ImageService imageService) {
        this.imageProperties = imageProperties;
        this.utilFileService = utilFileService;
        this.imageService = imageService;
    }

    @Transactional
    public ImageRequest updateFile(Long id, MultipartFile file) {
        return saveFileByEntityId(id, file);
    }

    @Transactional
    public ImageRequest saveFileByEntityId(Long id, MultipartFile file) {
        try {
            ImageRequest imageRequest = imageService.findOne(id);
            String path = utilFileService.generatePath(imageProperties.getFolder(),
                    imageRequest.getImageName(),
                    FileExtension.JPEG.getExtension());
            utilFileService.createNewFile(path, file);
            imageRequest.setContainsImage(true);

            return imageService.update(imageRequest);
        } catch (IOException e) {
            throw new BadFileInRequestException(e.getMessage());
        }
    }

    @Transactional
    public FileSystemResource getFileByEntityId(Long id) {
        ImageRequest imageRequest = imageService.findOne(id);
        String path = utilFileService.generatePath(imageProperties.getFolder(),
                imageRequest.getImageName(),
                FileExtension.JPEG.getExtension());
        FileSystemResource fileResource = new FileSystemResource(path);

        return fileResource;
    }

    @Transactional
    public ImageRequest deleteFileByEntityId(Long id) {
        ImageRequest imageRequest = imageService.findOne(id);
        String path = utilFileService.generatePath(imageProperties.getFolder(),
                imageRequest.getImageName(),
                FileExtension.JPEG.getExtension());
        if (utilFileService.deleteFileByPath(path)) {
            imageRequest.setContainsImage(false);

            return imageService.update(imageRequest);
        } else {
            throw new BadFileForResponseException("Not found file for entity with uuid - " +
                    imageRequest.getImageName());
        }
    }
}
