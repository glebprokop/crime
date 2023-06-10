package org.sfec.controller.fingerprint;

import jakarta.validation.Valid;
import org.sfec.controller.CrudController;
import org.sfec.entity.common.EntityStatus;
import org.sfec.entity.fingerprint.dto.FingerprintRequest;
import org.sfec.service.fingerprint.FingerprintService;
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
@RequestMapping("/api/v1/fingerprints")
public class FingerprintController implements CrudController<FingerprintRequest> {

    private final FingerprintService fingerprintService;

    public FingerprintController(FingerprintService fingerprintService) {
        this.fingerprintService = fingerprintService;
    }

    @GetMapping()
    public ResponseEntity<List<FingerprintRequest>> findAllByEntityStatus(
            @Valid @RequestParam(name = "status") EntityStatus entityStatus) {
        List<FingerprintRequest> objects = fingerprintService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FingerprintRequest> findOne(@Valid @PathVariable Long id) {
        FingerprintRequest object = fingerprintService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@Valid @PathVariable Long id) {
        fingerprintService.softDelete(id);
    }

    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@Valid @PathVariable Long id) {
        fingerprintService.hardDelete(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<FingerprintRequest> create(@Valid @RequestBody FingerprintRequest fingerprintRequest) {
        FingerprintRequest created = fingerprintService.create(fingerprintRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    @PutMapping()
    public ResponseEntity<FingerprintRequest> update(@Valid @RequestBody FingerprintRequest fingerprintRequest) {
        FingerprintRequest updated = fingerprintService.update(fingerprintRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}