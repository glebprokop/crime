package org.sfec.repository.image;

import org.sfec.entity.image.Image;
import org.sfec.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for {@link Image} entity
 */
@Repository
public interface ImageRepository extends CrudRepository<Image> {
}