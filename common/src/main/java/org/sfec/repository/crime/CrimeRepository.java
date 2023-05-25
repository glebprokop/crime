package org.sfec.repository.crime;

import org.sfec.entity.crime.Crime;
import org.sfec.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for {@link Crime} entity
 */
@Repository
public interface CrimeRepository extends CrudRepository<Crime> {
}