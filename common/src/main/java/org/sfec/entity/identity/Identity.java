package org.sfec.entity.identity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sfec.entity.BaseEntity;
import org.sfec.entity.fingerprint.Fingerprint;

import java.sql.Timestamp;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false,
        exclude = {"fingerprint"})
@ToString(exclude = {"fingerprint"})
@Entity
@Table(name = "m_identity")
public class Identity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "m_identity_identity_id_seq")
    @SequenceGenerator(name = "m_identity_identity_id_seq",
            sequenceName = "m_identity_identity_id_seq",
            allocationSize = 1)
    @Column(name = "identity_id")
    private Long id;

    @Column(name = "identity_date")
    private Timestamp identityDate;

    @Column(name = "person_first_name")
    private String personFirstName;

    @Column(name = "person_second_name")
    private String personSecondName;

    @Column(name = "person_last_name")
    private String personLastName;

    @Column(name = "person_birth_date")
    private Timestamp personBirthDate;

    @Column(name = "comment")
    private String comment;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fingerprint_id", unique = true)
    @JsonManagedReference
    private Fingerprint fingerprint;
}
