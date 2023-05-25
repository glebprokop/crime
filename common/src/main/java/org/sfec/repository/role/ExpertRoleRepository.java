package org.sfec.repository.role;

import org.sfec.entity.role.ExpertRole;
import org.sfec.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for {@link ExpertRole} entity
 */
@Repository
public interface ExpertRoleRepository extends CrudRepository<ExpertRole> {
}