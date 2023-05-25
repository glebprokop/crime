package org.sfec.repository.department;

import org.sfec.entity.department.ExpertDepartment;
import org.sfec.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for {@link ExpertDepartment} entity
 */
@Repository
public interface ExpertDepartmentRepository extends CrudRepository<ExpertDepartment> {
}