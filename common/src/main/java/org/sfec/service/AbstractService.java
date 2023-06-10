package org.sfec.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.sfec.entity.BaseEntity;
import org.sfec.entity.BaseRequestDto;
import org.sfec.entity.common.EntityStatus;
import org.sfec.exception.EntityNotFoundException;
import org.sfec.mapper.CommonMapper;
import org.sfec.repository.CrudRepository;
import org.sfec.util.TimeManager;
import org.springframework.cache.annotation.Cacheable;

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
 *            all service methods need to return such objects
 * @param <R> JPA based repository class, creating for every entity (see {@link CrudRepository})
 */
@Data
@Cacheable("entities")
public abstract class AbstractService<E extends BaseEntity,
        T extends BaseRequestDto,
        R extends CrudRepository<E>>
        implements CrudService<T> {

    private final CommonMapper<E, T> mapper;

    private final R repository;

    private final TimeManager timeManager;

    public AbstractService(CommonMapper<E, T> mapper, R repository, TimeManager timeManager) {
        this.mapper = mapper;
        this.repository = repository;
        this.timeManager = timeManager;
    }

    @Override
    @Transactional
    public List<T> findAll() {
        List<E> eList = repository.findAll();

        return mapper.toTransfer(eList);
    }

    @Override
    @Transactional
    public List<T> findAllByEntityStatus(EntityStatus entityStatus) {
        List<E> eList = repository.findAllByEntityStatus(entityStatus);

        return mapper.toTransfer(eList);
    }

    @Override
    @Transactional
    public Optional<T> findById(Long id) {
        existById(id);
        Optional<E> optionalE = repository.findById(id);
        T t = mapper.toTransfer(optionalE.get());

        return Optional.of(t);
    }

    @Override
    @Transactional
    public T findOne(Long id) {
        return this.findById(id).get();
    }

    @Override
    @Transactional
    public void hardDelete(Long id) {
        existById(id);
        repository.deleteById(id);
        repository.flush();
    }

    @Override
    @Transactional
    public void softDelete(Long id) {
        existById(id);

        T t = this.findOne(id);
        t.setEntityStatus(EntityStatus.DELETED);
        this.update(t);
    }

    @Override
    @Transactional
    public T create(T t) {
        E e = mapper.toEntity(t);
        e.setChanged(timeManager.currentTime());
        e.setCreated(timeManager.currentTime());

        T createdT = mapper.toTransfer(repository.save(e));

        return createdT;
    }

    @Override
    @Transactional
    public T update(T t) {
        existById(t.getId());

        E e = mapper.toEntity(t);
        e.setChanged(timeManager.currentTime());
        T updatedT = mapper.toTransfer(repository.save(e));

        return updatedT;
    }

    @Override
    @Transactional
    public Boolean existById(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("id", id);
        }

        return true;
    }
}
