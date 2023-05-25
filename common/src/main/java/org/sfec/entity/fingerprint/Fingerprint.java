package org.sfec.entity.fingerprint;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
import org.sfec.entity.crime.Crime;
import org.sfec.entity.expert.Expert;
import org.sfec.entity.image.Image;

import java.sql.Timestamp;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"crime"})
@Entity
@Table(name = "m_fingerprint")
public class Fingerprint extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "m_fingerprint_fingerprint_id_seq")
    @SequenceGenerator(name = "m_fingerprint_fingerprint_id_seq",
            sequenceName = "m_fingerprint_fingerprint_id_seq",
            allocationSize = 1)
    @Column(name = "fingerprint_id")
    private Long id;

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "fingerprint_find_date")
    private Timestamp fingerprintFindDate;

    @Column(name = "serial_number")
    private Long serialNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expert_id",
            referencedColumnName = "expert_id")
    private Expert expert;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crime_id",
            referencedColumnName = "crime_id")
    private Crime crime;

    @OneToOne
    @JoinColumn(name = "image_id", unique = true)
    @JsonManagedReference
    private Image image;
}
