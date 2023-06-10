package org.sfec.entity.image;

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
import org.sfec.entity.fingerprint.Fingerprint;

import java.util.UUID;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "m_image")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "m_image_image_id_seq")
    @SequenceGenerator(name = "m_image_image_id_seq",
            sequenceName = "m_image_image_id_seq",
            allocationSize = 1)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_name", unique = true)
    private UUID imageName;

    @Column(name = "contains_image")
    private Boolean containsImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fingerprint_id",
            referencedColumnName = "fingerprint_id")
    @JsonManagedReference
    private Fingerprint fingerprint;
}