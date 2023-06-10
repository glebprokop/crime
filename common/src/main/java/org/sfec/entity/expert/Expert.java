package org.sfec.entity.expert;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseEntity;
import org.sfec.entity.department.ExpertDepartment;
import org.sfec.entity.role.ExpertRole;

/**
 * Entity class described the expert - logged user and main worker
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(
        callSuper = false,
        exclude = {"expertRole", "expertDepartment"})
@ToString(exclude = {"expertRole", "expertDepartment"})
@Entity
@Table(name = "m_expert")
public class Expert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "m_expert_expert_id_seq")
    @SequenceGenerator(name = "m_expert_expert_id_seq",
            sequenceName = "m_expert_expert_id_seq",
            allocationSize = 1)
    @Column(name = "expert_id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expert_role_id",
            referencedColumnName = "expert_role_id")
    @JsonManagedReference
    private ExpertRole expertRole;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expert_department_id",
            referencedColumnName = "expert_department_id")
    @JsonManagedReference
    private ExpertDepartment expertDepartment;
}