package org.sfec.controller.image;

import org.sfec.controller.CrudController;
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
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController implements CrudController<ImageRequest> {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    
    @GetMapping()
    public ResponseEntity<List<ImageRequest>> findAllByEntityStatus(
            @RequestParam(name = "status") EntityStatus entityStatus) {
        List<ImageRequest> objects = imageService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ImageRequest> findOne(@PathVariable Long id) {
        ImageRequest object = imageService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable Long id) {
        imageService.softDelete(id);
    }

    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@PathVariable Long id) {
        imageService.hardDelete(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<ImageRequest> create(@RequestBody ImageRequest imageRequest) {
        ImageRequest created = imageService.create(imageRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    @PutMapping()
    public ResponseEntity<ImageRequest> update(@RequestBody ImageRequest imageRequest) {
        ImageRequest updated = imageService.update(imageRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
