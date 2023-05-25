package org.sfec.repository.fingerprint;

import org.sfec.entity.fingerprint.Fingerprint;
import org.sfec.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for {@link Fingerprint} entity
 */
@Repository
public interface FingerprintRepository extends CrudRepository<Fingerprint> {
}