package org.sfec.repository.crime;

import org.sfec.entity.crime.Crime;
import org.sfec.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Repository class for {@link Crime} entity
 */
@Repository
public interface CrimeRepository extends CrudRepository<Crime> {

    @Modifying(flushAutomatically = true)
    @Query(value = "call delete_before_date_pr(:date);", nativeQuery = true)
    void deleteBeforeDate(@Param("date") Timestamp date);
}