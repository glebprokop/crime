package org.sfec.controller.department;

import org.sfec.controller.CrudController;
import org.sfec.entity.common.EntityStatus;
import org.sfec.entity.department.dto.ExpertDepartmentRequest;
import org.sfec.service.department.ExpertDepartmentService;
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
@RequestMapping("api/v1/expert-departments")
public class ExpertDepartmentController implements CrudController<ExpertDepartmentRequest> {

    private final ExpertDepartmentService expertDepartmentService;

    public ExpertDepartmentController(ExpertDepartmentService expertDepartmentService) {
        this.expertDepartmentService = expertDepartmentService;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<ExpertDepartmentRequest>> findAllByEntityStatus(
            @RequestParam(name = "status") EntityStatus entityStatus) {
        List<ExpertDepartmentRequest> objects = expertDepartmentService.findAllByEntityStatus(entityStatus);

        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ExpertDepartmentRequest> findOne(@PathVariable Long id) {
        ExpertDepartmentRequest object = expertDepartmentService.findOne(id);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable Long id) {
        expertDepartmentService.softDelete(id);
    }

    @Override
    @DeleteMapping("/erase/{id}")
    public void hardDelete(@PathVariable Long id) {
        expertDepartmentService.hardDelete(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<ExpertDepartmentRequest> create(@RequestBody ExpertDepartmentRequest ExpertDepartmentRequest) {
        ExpertDepartmentRequest created = expertDepartmentService.create(ExpertDepartmentRequest);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    @PutMapping()
    public ResponseEntity<ExpertDepartmentRequest> update(@RequestBody ExpertDepartmentRequest ExpertDepartmentRequest) {
        ExpertDepartmentRequest updated = expertDepartmentService.update(ExpertDepartmentRequest);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }   
}
