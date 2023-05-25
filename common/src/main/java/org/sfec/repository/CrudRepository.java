package org.sfec.repository;

import org.sfec.entity.BaseEntity;
import org.sfec.entity.common.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CrudRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

    /**
     * Method for searching entities by {@link EntityStatus} status of
     * enable
     *
     * @param entityStatus {@link EntityStatus} status of object
     * @return list of all entities with such status
     */
    List<E> findAllByEntityStatus(EntityStatus entityStatus);
}
