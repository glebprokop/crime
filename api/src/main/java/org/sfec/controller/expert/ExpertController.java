package org.sfec.controller.expert;

import jakarta.validation.Valid;
import org.sfec.controller.CrudController;
import org.sfec.entity.common.EntityStatus;
import org.sfec.entity.expert.dto.ExpertRequest;
import org.sfec.service.expert.ExpertService;
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
@RequestMapping("/api/v1/experts")
public class ExpertController implements CrudController<ExpertRequest> {

    private final ExpertService expertService;

    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @GetMapping()
    public ResponseEntity<List<ExpertRequest>> findAllByEntityStatus(
            @Valid @RequestParam(name = "status") EntityStatus entityStatus) {
        List<ExpertRequest> objects = expertService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ExpertRequest> findOne(@Valid @PathVariable Long id) {
        ExpertRequest object = expertService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@Valid @PathVariable Long id) {
        expertService.softDelete(id);
    }

    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@Valid @PathVariable Long id) {
        expertService.hardDelete(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<ExpertRequest> create(@Valid @RequestBody ExpertRequest expertRequest) {
        ExpertRequest created = expertService.createWithPasswordEncode(expertRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    @PutMapping()
    public ResponseEntity<ExpertRequest> update(@Valid @RequestBody ExpertRequest expertRequest) {
        ExpertRequest updated = expertService.updateWithPasswordEncode(expertRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
