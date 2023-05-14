package org.sfec.repository.expert;

import org.sfec.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ExpertRepository extends JpaRepository<Expert, Long> {

    Expert findByUsername(String name);

}
