package org.sfec.service;

import java.util.List;
import java.util.Optional;

/**
 * Parametrised interface used to describe the all CRUD operations on the org.sfec.common.service` layer.
 * All services in application need to implement this interface.
 *
 * @param <T> the org.sfec.common.entity class parameter
 */
public interface CrudService<T>{

    List<T> findAll();

    Optional<T> findById(Long id);

    T findOne(Long id);

    void delete(Long id);

    T create(T t);

    T update(T t);
}
