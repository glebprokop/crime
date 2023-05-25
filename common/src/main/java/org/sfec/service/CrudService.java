package org.sfec.service;

import jakarta.validation.Valid;
import org.sfec.entity.common.EntityStatus;
import org.sfec.exception.EntityNotFoundException;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * Parametrised interface used to describe the all CRUD operations on
 * the service layer.
 * All services in application need to implement this interface.
 *
 * @param <T> the entity class parameter
 */
@Validated
public interface CrudService<T>{

    List<T> findAll();

    List<T> findAllByEntityStatus(@Valid EntityStatus entityStatus);

    Optional<T> findById(@Valid Long id);

    T findOne(@Valid Long id);

    void hardDelete(@Valid Long id);

    void softDelete(@Valid Long id);

    T create(@Valid T t);

    T update(@Valid T t);

    Boolean existById(@Valid Long id) throws EntityNotFoundException;
}
