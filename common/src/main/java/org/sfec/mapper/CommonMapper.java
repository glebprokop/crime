package org.sfec.mapper;

import org.sfec.entity.BaseEntity;
import org.sfec.entity.BaseRequestDto;

import java.util.List;

/**
 * Interface describing actions of {@link org.mapstruct.Mapper}`s classes, allows to convert entity to data transfer
 * object (or dto) and inverse it back.
 *
 * @param <E> entity object based on {@link BaseEntity}
 * @param <T> data transfer object based on {@link BaseRequestDto}
 */
public interface CommonMapper<E, T> {

    /**
     * Method to create entity object from dto object
     *
     * @param t dto object
     * @return entity object
     */
    E toEntity(T t);

    /**
     * Method to create {@link List} of entity objects from {@link List} of dto objects
     *
     * @param t {@link List} of dto object
     * @return entity object
     */
    List<E> toEntity(List<T> t);

    /**
     * Method to create dto object from entity object
     *
     * @param e entity object
     * @return dto object
     */
    T toTransfer(E e);

    /**
     * Method to create {@link List} of dto objects from {@link List} of entity objects
     *
     * @param e {@link List} of entity object
     * @return entity object
     */
    List<T> toTransfer(List<E> e);
}
