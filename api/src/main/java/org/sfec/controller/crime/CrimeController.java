package org.sfec.controller.crime;

import jakarta.validation.Valid;
import org.sfec.controller.CrudController;
import org.sfec.entity.common.EntityStatus;
import org.sfec.entity.crime.dto.CrimeRequest;
import org.sfec.service.crime.CrimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("api/v1/crimes")
@Validated
public class CrimeController implements CrudController<CrimeRequest> {

    private final CrimeService crimeService;

    public CrimeController(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<CrimeRequest>> findAllByEntityStatus(
            @Valid @RequestParam EntityStatus entityStatus) {
        List<CrimeRequest> objects = crimeService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CrimeRequest> findOne(@Valid @PathVariable Long id) {
        CrimeRequest object = crimeService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@Valid @PathVariable Long id) {
        crimeService.softDelete(id);
    }

    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@Valid @PathVariable Long id) {
        crimeService.hardDelete(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<CrimeRequest> create(@Valid @RequestBody CrimeRequest CrimeRequest) {
        CrimeRequest created = crimeService.create(CrimeRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    @PutMapping()
    public ResponseEntity<CrimeRequest> update(@Valid @RequestBody CrimeRequest CrimeRequest) {
        CrimeRequest updated = crimeService.update(CrimeRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/by-date/")
    public ResponseEntity<Object> softDeleteBeforeDate(@RequestParam Timestamp date) {
        crimeService.deleteBeforeDate(date);

        return new ResponseEntity<>("Objects deleted", HttpStatus.OK);
    }
}
