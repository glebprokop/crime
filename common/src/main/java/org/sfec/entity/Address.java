package org.sfec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Entity class for description addresses of crimes.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "m_address")
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_address_id_seq")
    @SequenceGenerator(name = "m_address_id_seq",
            sequenceName = "m_address_id_seq",
            allocationSize = 1,
            initialValue = 100)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "region")
    private String region;

    @Column(name = "district")
    private String district;

    @Column(name = "locality")
    private String locality;

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
}
