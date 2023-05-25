package org.sfec.entity.department;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseEntity;
import org.sfec.entity.expert.Expert;

import java.util.Set;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(
        callSuper = false,
        exclude = {"experts"})
@ToString
@Entity
@Table(name = "m_expert_department")
public class ExpertDepartment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "m_expert_department_expert_department_id_seq")
    @SequenceGenerator(name = "m_expert_department_expert_department_id_seq",
            sequenceName = "m_expert_department_expert_department_id_seq",
            allocationSize = 1)
    @Column(name = "expert_department_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "expertDepartment",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Expert> experts;
}
