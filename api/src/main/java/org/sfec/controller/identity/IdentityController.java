package org.sfec.controller.identity;

import org.sfec.controller.CrudController;
import org.sfec.entity.common.EntityStatus;
import org.sfec.entity.identity.dto.IdentityRequest;
import org.sfec.service.identity.IdentityService;
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
@RequestMapping("/api/v1/identities")
public class IdentityController implements CrudController<IdentityRequest> {

    private final IdentityService identityService;

    public IdentityController(IdentityService identityService) {
        this.identityService = identityService;
    }

    @GetMapping()
    public ResponseEntity<List<IdentityRequest>> findAllByEntityStatus(
            @RequestParam(name = "status") EntityStatus entityStatus) {
        List<IdentityRequest> objects = identityService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<IdentityRequest> findOne(@PathVariable Long id) {
        IdentityRequest object = identityService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable Long id) {
        identityService.softDelete(id);
    }

    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@PathVariable Long id) {
        identityService.hardDelete(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<IdentityRequest> create(@RequestBody IdentityRequest identityRequest) {
        IdentityRequest created = identityService.create(identityRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    @PutMapping()
    public ResponseEntity<IdentityRequest> update(@RequestBody IdentityRequest identityRequest) {
        IdentityRequest updated = identityService.update(identityRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
