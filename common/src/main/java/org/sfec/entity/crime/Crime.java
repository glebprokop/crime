package org.sfec.entity.crime;

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
import org.sfec.entity.address.Address;
import org.sfec.entity.fingerprint.Fingerprint;

import java.sql.Timestamp;
import java.util.List;

/**
 * Entity class for description addresses of crimes.
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString()
@Entity
@Table(name = "m_crime")
public class Crime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "m_crime_crime_id_seq")
    @SequenceGenerator(name = "m_crime_crime_id_seq",
            sequenceName = "m_crime_crime_id_seq",
            allocationSize = 1,
            initialValue = 100)
    @Column(name = "crime_id")
    private Long id;

    @Column(name = "police_reg_number")
    private Long policeRegNumber;

    @Column(name = "case_number", unique = true)
    private Long caseNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "criminal_code_article")
    private Long criminalCodeArticle;

    @Column(name = "crime_date")
    private Timestamp crimeDate;

    @Column(name = "crime_status")
    private String crimeStatus;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "crime",
            cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Address> addresses;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "crime",
            cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Fingerprint> fingerprints;
}
