package org.sfec.entity.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseEntity;
import org.sfec.entity.expert.Expert;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_expert_role")
public class ExpertRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "m_expert_role_expert_role_id_seq")
    @SequenceGenerator(name = "m_expert_role_expert_role_id_seq",
            sequenceName = "m_expert_role_expert_role_id_seq",
            allocationSize = 1)
    @Column(name = "expert_role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "expertRole",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Expert> experts;
}
