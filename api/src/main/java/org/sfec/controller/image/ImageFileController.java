package org.sfec.controller.image;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.sfec.entity.image.dto.ImageRequest;
import org.sfec.service.image.ImageFileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images/{id}/files")
public class ImageFileController {

    private final ImageFileService imageFileService;

    public ImageFileController(ImageFileService imageFileService) {
        this.imageFileService = imageFileService;
    }

    @Operation(
            summary = "Find and get photo file for entity by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Photo file",
                            content = @Content(mediaType = "application/octet-stream")
                    )
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    Resource getFileByEntityId(@PathVariable(name = "id") Long id) {
        FileSystemResource resource = imageFileService.getFileByEntityId(id);

        return resource;
    }

    @Operation(
            summary = "Add photo file for entity by id",
            description = "Save photo file in file system with unique name",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Photo file",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageRequest> saveFileByEntityId(@PathVariable(name = "id") Long id,
                                                           @RequestParam(name = "file") MultipartFile file) {
        ImageRequest entityWithFileCreated = imageFileService.saveFileByEntityId(id, file);

        return new ResponseEntity<>(entityWithFileCreated, HttpStatus.OK);
    }

    @Operation(
            summary = "Update photo file for entity by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Photo file",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageRequest> updateFileByEntityId(@PathVariable(name = "id") Long id,
                                                             @RequestParam(name = "file") MultipartFile file) {
        ImageRequest entityWithFileUpdated = imageFileService.updateFile(id, file);

        return new ResponseEntity<>(entityWithFileUpdated, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ImageRequest> deleteFileByEntityId(@PathVariable(name = "id") Long id) {
        ImageRequest entityWithoutFile = imageFileService.deleteFileByEntityId(id);

        return new ResponseEntity<>(entityWithoutFile, HttpStatus.OK);
    }
}
