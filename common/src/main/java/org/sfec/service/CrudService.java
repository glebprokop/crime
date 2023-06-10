package org.sfec.service;

import org.sfec.entity.common.EntityStatus;
import org.sfec.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Parametrised interface used to describe the all CRUD operations on
 * the service layer.
 * All services in application need to implement this interface.
 *
 * @param <T> the entity class parameter
 */
public interface CrudService<T> {

    List<T> findAll();

    List<T> findAllByEntityStatus(EntityStatus entityStatus);

    Optional<T> findById(Long id);

    T findOne(Long id);

    void hardDelete(Long id);

    void softDelete(Long id);

    T create(T t);

    T update(T t);

    Boolean existById(Long id) throws EntityNotFoundException;
}
