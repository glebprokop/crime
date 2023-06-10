package org.sfec.entity.address;

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
import org.sfec.entity.crime.Crime;

/**
 * Entity class for description addresses of crimes.
 */
@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Entity
@Table(name = "m_address")
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "m_address_id_seq")
    @SequenceGenerator(name = "m_address_id_seq",
            sequenceName = "m_address_id_seq",
            allocationSize = 1,
            initialValue = 100)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "region")
    private String region;

    @Column(name = "district")
    private String district;

    @Column(name = "locality")
    private String locality;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "corps")
    private String corps;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "comment")
    private String comment;

    @Column(name = "police_department")
    private String policeDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crime_id",
            referencedColumnName = "crime_id")
    private Crime crime;
}
