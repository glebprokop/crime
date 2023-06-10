package org.sfec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.common.EntityStatus;

import java.sql.Timestamp;

@MappedSuperclass
@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private EntityStatus entityStatus;

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }
}
