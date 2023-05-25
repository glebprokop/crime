package org.sfec.service;

import jakarta.validation.Valid;
import lombok.Data;
import org.sfec.entity.BaseEntity;
import org.sfec.entity.BaseRequestDto;
import org.sfec.entity.common.EntityStatus;
import org.sfec.exception.EntityNotFoundException;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.CrudRepository;
import org.sfec.util.TimeManager;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

/**
 * Abstract service class, described all available CRUD operations according to the
 * {@link CrudService} interface. Every entity use implementation of this class to
 * get access to the datasource using the {@link CrudRepository} repository implementation.<p>
 * So, to create new service for entity, you need to define {@link org.mapstruct.Mapper} object
 * for convert "entity-dto" (based on the {@link CommonMapper} interface), and {@link CrudRepository} implementation for entity based on the
 * {@link org.springframework.data.jpa.repository.JpaRepository} standard.
 *
 * @param <E> entity object based on {@link BaseEntity}
 * @param <T> data transfer object based on {@link BaseRequestDto}. According to my contract,
 *           all service methods need to return such objects
 * @param <R> JPA based repository class, creating for every entity (see {@link CrudRepository})
 */
@Validated
@Data
public abstract class AbstractService<E extends BaseEntity,
        T extends BaseRequestDto,
        R extends CrudRepository<E>>
        implements CrudService<T>  {

    private final CommonMapper<E, T> mapper;

    private final R repository;

    private final TimeManager timeManager;

    public AbstractService(CommonMapper<E, T> mapper, R repository, TimeManager timeManager) {
        this.mapper = mapper;
        this.repository = repository;
        this.timeManager = timeManager;
    }

    @Override
    public List<T> findAll() {
        List<E> eList = repository.findAll();

        return mapper.toTransfer(eList);
    }

    @Override
    public List<T> findAllByEntityStatus(@Valid EntityStatus entityStatus){
        List<E> eList = repository.findAllByEntityStatus(entityStatus);

        return mapper.toTransfer(eList);
    }

    @Override
    public Optional<T> findById(@Valid Long id){
        existById(id);
        Optional<E> optionalE = repository.findById(id);
        T t = mapper.toTransfer(optionalE.get());

        return Optional.of(t);
    }

    @Override
    public T findOne(@Valid Long id){
        return this.findById(id).get();
    }

    @Override
    public void hardDelete(@Valid Long id){
        existById(id);

        repository.deleteById(id);
    }

    @Override
    public void softDelete(@Valid Long id){
        existById(id);

        T t = this.findOne(id);
        t.setEntityStatus(EntityStatus.DELETED);
        this.update(t);
    }

    @Override
    public T create(@Valid T t){
        E e = mapper.toEntity(t);
        e.setChanged(timeManager.currentTime());
        e.setCreated(timeManager.currentTime());

        T createdT = mapper.toTransfer(repository.save(e));

        return createdT;
    }

    @Override
    public T update(@Valid T t){
        existById(t.getId());

        E e = mapper.toEntity(t);
        e.setChanged(timeManager.currentTime());
        T updatedT = mapper.toTransfer(repository.save(e));

        return updatedT;
    }

    @Override
    public Boolean existById(@Valid Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("id", id);
        }

        return true;
    }
}
