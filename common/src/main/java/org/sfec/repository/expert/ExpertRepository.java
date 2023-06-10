package org.sfec.repository.expert;

import org.sfec.entity.department.ExpertDepartment;
import org.sfec.entity.expert.Expert;
import org.sfec.entity.role.ExpertRole;
import org.sfec.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class used for searching info about {@link Expert} objects in
 * the datasource
 */
@Repository
public interface ExpertRepository extends CrudRepository<Expert> {

    /**
     * Method used for searching user by his username, used for
     * security module
     *
     * @param username name of user, hah so unexpectedly
     * @return {@link Expert} object founded in data source
     */
    Optional<Expert> findByUsername(String username);

    /**
     * Method for searching {@link Expert} list for their {@link org.sfec.entity.role.ExpertRole}
     *
     * @return {@link Expert} object founded in data source
     */
    List<Expert> findAllByExpertRole(ExpertRole expertRole);

    /**
     * Method for searching {@link Expert} list for their {@link org.sfec.entity.department.ExpertDepartment}
     *
     * @return {@link Expert} object founded in data source
     */
    List<Expert> findAllByExpertDepartment(ExpertDepartment expertDepartment);
}
