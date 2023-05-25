package org.sfec.repository.address;

import org.sfec.entity.address.Address;
import org.sfec.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for {@link Address} entity
 */
@Repository
public interface AddressRepository extends CrudRepository<Address> {
}