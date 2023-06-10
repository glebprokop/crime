package org.sfec.controller.image;

import jakarta.validation.Valid;
import org.sfec.entity.common.EntityStatus;
import org.sfec.entity.image.dto.ImageRequest;
import org.sfec.service.image.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController extends AbstractImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        super(imageService);
        this.imageService = imageService;
    }

    @GetMapping()
    public ResponseEntity<List<ImageRequest>> findAllByEntityStatus(
            @Valid @RequestParam(name = "status") EntityStatus entityStatus) {
        List<ImageRequest> objects = imageService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ImageRequest> findOne(@Valid @PathVariable Long id) {
        ImageRequest object = imageService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@Valid @PathVariable Long id) {
        imageService.softDelete(id);
    }

    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@Valid @PathVariable Long id) {
        imageService.hardDelete(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<ImageRequest> create(@Valid @RequestBody ImageRequest imageRequest) throws IOException {
        ImageRequest created = imageService.createWithUuid(imageRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    @PutMapping()
    public ResponseEntity<ImageRequest> update(@Valid @RequestBody ImageRequest imageRequest) {
        ImageRequest updated = imageService.updateWithUuid(imageRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}