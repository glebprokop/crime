package org.sfec.controller;

import jakarta.validation.Valid;
import org.sfec.entity.BaseRequestDto;
import org.sfec.entity.common.EntityStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface CrudController<T extends BaseRequestDto> {

    ResponseEntity<List<T>> findAllByEntityStatus(@Valid @RequestParam(name = "status") EntityStatus entityStatus);

    ResponseEntity<T> findOne(@Valid Long id);

    void softDelete(@Valid Long id);

    void hardDelete(@Valid Long id);

    ResponseEntity<T> create(@Valid T t) throws IOException;

    ResponseEntity<T> update(@Valid T t);
}