package org.sfec.controller;

import org.sfec.entity.BaseRequestDto;
import org.sfec.entity.common.EntityStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

public interface CrudController<T extends BaseRequestDto> {

    ResponseEntity<List<T>> findAllByEntityStatus(@RequestParam(name = "status") EntityStatus entityStatus);

    ResponseEntity<T> findOne(@PathVariable Long id);

    void softDelete(@PathVariable Long id);

    void hardDelete(@PathVariable Long id);

    ResponseEntity<T> create(@RequestBody T t);

    ResponseEntity<T> update(@RequestBody T t);
}
